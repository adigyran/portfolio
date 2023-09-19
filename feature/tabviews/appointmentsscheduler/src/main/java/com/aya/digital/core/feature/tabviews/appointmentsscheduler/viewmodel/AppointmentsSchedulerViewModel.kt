package com.aya.digital.core.feature.tabviews.appointmentsscheduler.viewmodel

import com.aya.digital.core.data.base.result.models.appointment.SelectAppointmentResultModel
import com.aya.digital.core.domain.appointment.base.GetAppointmentsWithParticipantsByStartDayAndDaysUseCase
import com.aya.digital.core.domain.appointment.base.GetAppointmentsWithParticipantsUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileBriefUseCase
import com.aya.digital.core.domain.schedule.doctor.scheduler.GetDaySlotsScheduleUseCase
import com.aya.digital.core.domain.schedule.doctor.scheduler.GetMonthScheduleUseCase
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.navigation.AppointmentsSchedulerNavigationEvents
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.util.requestcodes.RequestCodes
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.Clock
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.ZoneId

class AppointmentsSchedulerViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val rootCoordinatorRouter: CoordinatorRouter,
    private val getProfileBriefUseCase: GetProfileBriefUseCase,
    private val getMonthScheduleUseCase: GetMonthScheduleUseCase,
    private val getDaySlotsScheduleUseCase: GetDaySlotsScheduleUseCase,
    private val getAppointmentsUseCase: GetAppointmentsWithParticipantsUseCase,
    private val getAppointmentsForDayUseCase: GetAppointmentsWithParticipantsByStartDayAndDaysUseCase
) :
    BaseViewModel<AppointmentsSchedulerState, AppointmentsSchedulerSideEffects>() {

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }

    override val container =
        container<AppointmentsSchedulerState, AppointmentsSchedulerSideEffects>(
            initialState = AppointmentsSchedulerState(),
        )
        { state ->
            getBriefProfile()
            initDayAndMonthByCurrentTime()
        }

    private fun getBriefProfile() = intent {
        getProfileBriefUseCase().await()
            .processResult({profileModel->
                           reduce { state.copy(doctorName = profileModel.firstName) }
            },{processError(it)})
    }

    private fun initDayAndMonthByCurrentTime() = intent {
        val currentInstant = Clock.systemDefaultZone().instant()
        val currentLocalDate =
            LocalDateTime.ofInstant(currentInstant, ZoneId.systemDefault()).toLocalDate()
        initDayAndMonth(currentLocalDate)

    }

    private fun initDayAndMonth(date: LocalDate) = intent {

        val currentMonth = YearMonth.of(date.year, date.month)
        reduce {
            state.copy(currentMonth = currentMonth, currentDay = date)
        }
        formMonthDays(currentMonth)
        selectDay(date)
    }

    private fun formMonthDays(month: YearMonth) = intent {
        getMonthScheduleUseCase(month).await()
            .processResult({ days ->
                reduce {
                    state.copy(
                        schedulerDays = days.map { it.toSchedulerDay() }
                    )
                }
            }, { processError(it) })
    }

    fun onDayClicked(day: LocalDate) = intent {
        selectDay(day)
    }

    fun onSlotClicked(slotId: Int) = openSlotAppointments(slotId)

    fun onCreateScheduleClicked()  = intent {
        val selectedDate = state.selectedDay
        selectedDate?.let {
            listenForScheduleCreation()
            coordinatorRouter.sendEvent(AppointmentsSchedulerNavigationEvents.CreateSlots(
                requestCode = RequestCodes.CREATE_SCHEDULE_REQUEST_CODE,
                date = selectedDate
            ))
        }
    }

    private fun listenForScheduleCreation() {
        rootCoordinatorRouter.setResultListener(RequestCodes.CREATE_SCHEDULE_REQUEST_CODE) {
            refreshDay()
        }
    }

    private fun openSlotAppointments(slotId: Int) = intent {
        when (val appointmentsCountForSlot = state.getAppointmentsCountForSlot(slotId)) {
            1 -> {
                val appointmentId = state.getFirstAppointmentForSlot(slotId)?.id
                appointmentId?.let {
                    coordinatorRouter.sendEvent(
                        AppointmentsSchedulerNavigationEvents.OpenAppointment(
                            appointmentId
                        )
                    )
                }
            }

            in 1..Int.MAX_VALUE -> {
                val slot = state.schedulerSlots?.firstOrNull { it.id == slotId }
                slot?.let {
                    listenForAppointmentSelect()
                    coordinatorRouter.sendEvent(
                        AppointmentsSchedulerNavigationEvents.OpenAppointmentsForSpecificSlot(
                            requestCode = RequestCodes.APPOINTMENTS_FOR_DATE_REQUEST_CODE,
                            startDateTime = slot.startDateTime,
                            endDateTime = slot.endDateTime
                        )
                    )
                }
            }

            else -> {

            }
        }
    }

    private fun listenForAppointmentSelect() {
        rootCoordinatorRouter.setResultListener(RequestCodes.APPOINTMENTS_FOR_DATE_REQUEST_CODE) { result ->
            if (result !is SelectAppointmentResultModel) return@setResultListener
            coordinatorRouter.sendEvent(AppointmentsSchedulerNavigationEvents.OpenAppointment(result.appointmentId))
        }
    }
    fun onRefresh() = intent {
        refreshDay()
    }

    private fun refreshDay() = intent {
        if (state.selectedDay == null) return@intent
        getSelectedDaySlots()
        getSelectedDayAppointments()
    }

    fun onSelectCalendarDate(selectedDate: LocalDate) {
        selectCalendarDate(selectedDate)
    }

    private fun selectCalendarDate(selectedDate: LocalDate) = intent {
        initDayAndMonth(selectedDate)
    }

    private fun selectDay(day: LocalDate) = intent {
        reduce { state.copy(selectedDay = day) }
        if (state.selectedDay == null) return@intent
        getSelectedDaySlots()
        getSelectedDayAppointments()
    }

    private fun getSelectedDaySlots() = intent {
        getDaySlotsScheduleUseCase(state.selectedDay!!).await()
            .processResult({ daySlots ->
                reduce { state.copy(schedulerSlots = daySlots.map { it.toSchedulerSlot() }) }
            }, { processError(it) })
    }

    private fun getSelectedDayAppointments() = intent(registerIdling = false) {
        getAppointmentsForDayUseCase(state.selectedDay!!).asFlow()
            .collect { resultModel ->
                resultModel.processResult({ appointments ->
                    reduce { state.copy(appointments = appointments.map { it.toAppointmentData() }) }

                }, {
                    processError(it)
                })
            }
    }

}

