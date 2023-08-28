package com.aya.digital.feature.bottomdialogs.createappointmentdialog.viewmodel

import com.aya.digital.core.data.base.result.models.appointment.CreateAppointmentResultModel
import com.aya.digital.core.domain.appointment.create.CreateAppointmentUseCase
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import com.aya.digital.core.domain.schedule.base.GetLatestScheduleByDoctorIdByDateUseCase
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.FieldsTags
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.navigation.CreateAppointmentDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui.CreateAppointmentDialogView
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.rx3.await
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toKotlinLocalDate
import kotlinx.datetime.toKotlinLocalDateTime
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class CreateAppointmentDialogViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val param: CreateAppointmentDialogView.Param,
    private val getLatestScheduleByDoctorIdByDateUseCase: GetLatestScheduleByDoctorIdByDateUseCase,
    private val createAppointmentUseCase: CreateAppointmentUseCase
) :
    BaseViewModel<CreateAppointmentDialogState, BaseSideEffect>() {
    override val container = container<CreateAppointmentDialogState, BaseSideEffect>(
        initialState = CreateAppointmentDialogState(
        ),
    )
    {

    }

    init {
        loadDaySlots()
    }

    private fun loadDaySlots() = intent(registerIdling = false) {
        param.date?.let { date ->
            getLatestScheduleByDoctorIdByDateUseCase(param.doctorId, date.toKotlinLocalDate())
                .asFlow()
                .collect { resultModel ->
                    resultModel.processResult({ slots ->
                        val selectedSlotId =
                            param.slotDateTime?.let { selectedSlot -> slots.firstOrNull { it.startDate == selectedSlot.toKotlinLocalDateTime() }?.id }
                        reduce {
                            state.copy(
                                slots = slots,
                                date = date.toKotlinLocalDate(),
                                selectedSlotId = selectedSlotId
                            )
                        }
                    }, { processError(it) })
                }
        }
    }

    fun onNameFieldChanged(tag: Int, text: String) = intent {
        when (tag) {
            FieldsTags.COMMENT_FIELD -> reduce { state.copy(comment = text) }
        }
    }

    fun close() = intent {
        coordinatorRouter.sendEvent(CreateAppointmentDialogNavigationEvents.Exit)
    }


    fun onBookClicked() = intent {
        bookAppointment()
    }

    private fun bookAppointment() = intent {
        if (state.selectedSlotId == null) return@intent
        createAppointmentUseCase(state.selectedSlotId!!, state.comment ?: "")
            .await()
            .processResult({ appointment ->
                onAppointmentCreated(appointment)
            }, { processError(it) })
    }

    private fun onAppointmentCreated(appointmentModel: AppointmentModel) = intent {
        coordinatorRouter.sendEvent(
            CreateAppointmentDialogNavigationEvents.FinishWithResult(
                param.requestCode,
                CreateAppointmentResultModel(
                    appointmentId = appointmentModel.id,
                    appointmentDateTime = appointmentModel.startDate.toKotlinLocalDateTime()
                )
            )
        )
    }

    fun onSlotClicked(slotId: Int, date: LocalDate?) = intent {
        reduce { state.copy(selectedSlotId = slotId) }
    }


    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) {
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}

