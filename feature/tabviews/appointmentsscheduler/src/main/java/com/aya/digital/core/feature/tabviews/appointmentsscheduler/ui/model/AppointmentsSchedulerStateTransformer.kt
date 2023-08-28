package com.aya.digital.core.feature.tabviews.appointmentsscheduler.ui.model

import android.content.Context
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.viewmodel.AppointmentData
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.viewmodel.AppointmentPatient
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.viewmodel.AppointmentsSchedulerState
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.viewmodel.DayStatus
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.viewmodel.SchedulerSlot
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.viewmodel.SchedulerStatusDay
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.viewmodel.getAppointmentsCountForSlot
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.viewmodel.getFirstAppointmentForSlot
import com.aya.digital.core.mvi.BaseStateTransformer
import com.aya.digital.core.ui.adapters.base.DiffItem
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerday.model.AppointmentsSchedulerDayUIModel
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model.AppointmentUi
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model.AppointmentUiStatus
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model.AppointmentsSchedulerEmptySlotUIModel
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model.AppointmentsSchedulerAppointmentSlotUIModel
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model.AppointmentsSchedulerEmptyDayUIModel
import com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model.AppointmentsSchedulerMultiAppointmentSlotUIModel
import com.aya.digital.core.util.datetime.DateTimeUtils
import kotlinx.datetime.toKotlinLocalDate
import kotlinx.datetime.toKotlinLocalTime
import java.time.LocalDate
import java.time.LocalDateTime

class AppointmentsSchedulerStateTransformer(
    private val context: Context,
    private val dateTimeUtils: DateTimeUtils
) : BaseStateTransformer<AppointmentsSchedulerState, AppointmentsSchedulerUiModel>() {
    override fun invoke(state: AppointmentsSchedulerState): AppointmentsSchedulerUiModel =
        AppointmentsSchedulerUiModel(
            daysData = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    state.schedulerDays?.run {
                        addAll(map { schedulerDay ->
                            AppointmentsSchedulerDayUIModel(
                                day = schedulerDay.day,
                                dayOfWeekName = schedulerDay.day.getDayOfWeekText(),
                                dayOfMonthText = schedulerDay.day.getDayOfMonthText(),
                                hasSlots = true, //TODO ask why we even have disabled days
                                selected = state.selectedDay?.let { it == schedulerDay.day }
                                    ?: false,
                                hasAppointments = schedulerDay.day.getHasAppointmentsStatus(state)
                            )
                        })
                    }
                }
            },
            slotsData = kotlin.run {
                return@run mutableListOf<DiffItem>().apply {
                    val schedulerSlots = state.schedulerSlots
                    if (schedulerSlots.isNullOrEmpty()) add(
                        AppointmentsSchedulerEmptyDayUIModel(state.doctorName?:"")
                    )
                    else {
                        schedulerSlots.run {
                            addAll(mapNotNull { scheduleSlot -> scheduleSlot.getScheduleSlotUi(state) })
                        }
                    }

                }
            },
            monthName = state.currentMonth?.atEndOfMonth()?.formatScheduleDate(),
            selectedDayDate = state.selectedDay

        )

    private fun LocalDate.getHasSlotsStatus(state: AppointmentsSchedulerState) =
        state.getDayStatus(this) { it.status is DayStatus.Active }

    private fun LocalDate.getHasAppointmentsStatus(state: AppointmentsSchedulerState) =
        state.getDayStatus(this) { it.status is DayStatus.Active && it.status.hasAppointments }

    private fun AppointmentsSchedulerState.getDayStatus(
        date: LocalDate,
        hasDay: (SchedulerStatusDay) -> Boolean
    ) = this.getSchedulerDaysWithStatus { schedulerStatusDays ->
        schedulerStatusDays.getDayByDate(date)?.let { hasDay(it) } ?: false
    }

    private fun List<SchedulerStatusDay>.getDayByDate(date: LocalDate) =
        this.firstOrNull { it.day == date }

    private fun AppointmentsSchedulerState.getSchedulerDaysWithStatus(predicate: (List<SchedulerStatusDay>) -> Boolean) =
        this.schedulerDaysWithStatus?.let { predicate(schedulerDaysWithStatus) } ?: false

    private fun LocalDate.getDayOfWeekText() =
        dateTimeUtils.formatDayOfWeekName(this.toKotlinLocalDate())

    private fun LocalDate.getDayOfMonthText() =
        dateTimeUtils.formatDayOfMonth(this.toKotlinLocalDate())

    private fun LocalDateTime.formatSlotTime() =
        dateTimeUtils.formatSchedulerSlotTime(this.toLocalTime().toKotlinLocalTime())

    private fun LocalDate.formatScheduleDate() =
        dateTimeUtils.formatSchedulerDateTime(this.toKotlinLocalDate())

    private fun SchedulerSlot.getScheduleSlotUi(state: AppointmentsSchedulerState) =
        when (val appointmentsCountForSlot = state.getAppointmentsCountForSlot(this.id)) {
            1 -> {
                val appointment = state.getFirstAppointmentForSlot(this.id)
                appointment?.let {
                    AppointmentsSchedulerAppointmentSlotUIModel(
                        id = id,
                        dateTime = startDateTime,
                        timeText = startDateTime.formatSlotTime(),
                        appointmentUi = appointment.toUiAppointment()
                    )
                }

            }

            in 1..Int.MAX_VALUE -> {
                AppointmentsSchedulerMultiAppointmentSlotUIModel(
                    id = id,
                    dateTime = startDateTime,
                    timeText = startDateTime.formatSlotTime(),
                    appointmentCountText = appointmentsCountForSlot.getAppointmentCountText()
                )
            }

            else -> {
                AppointmentsSchedulerEmptySlotUIModel(
                    id = id,
                    dateTime = startDateTime,
                    timeText = startDateTime.formatSlotTime()
                )
            }
        }

    private fun AppointmentModel.AppointmentStatus.toUiStatus() = when (this) {
        AppointmentModel.AppointmentStatus.SCHEDULED -> AppointmentUiStatus.SCHEDULED
        AppointmentModel.AppointmentStatus.DONE -> AppointmentUiStatus.DONE
        AppointmentModel.AppointmentStatus.CANCELLED -> AppointmentUiStatus.CANCELLED
    }

    private fun AppointmentData.toUiAppointment() = AppointmentUi(
        id = id,
        patient = patient?.toUiPatient(),
        status = status.toUiStatus(),
        isTelemedicine = isTelemed
    )

    private fun AppointmentPatient.toUiPatient() = AppointmentUi.AppointmentUiPatient(
        id = id,
        patientAvatar = avatarImageLink,
        name = lastName
    )

    private fun Int.getAppointmentCountText() = "%d appointments".format(this)
}

