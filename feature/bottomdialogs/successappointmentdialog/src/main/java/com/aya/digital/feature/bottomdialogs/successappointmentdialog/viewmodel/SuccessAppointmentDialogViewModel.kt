package com.aya.digital.feature.bottomdialogs.successappointmentdialog.viewmodel

import com.aya.digital.core.data.base.result.models.appointment.CreateAppointmentResultModel
import com.aya.digital.core.domain.appointment.base.GetAppointmentByIdWithParticipantUseCase
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.bottomdialogs.successappointmentdialog.navigation.SuccessAppointmentDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.successappointmentdialog.ui.SuccessAppointmentDialogView
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.rx3.await
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toKotlinLocalDateTime
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SuccessAppointmentDialogViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val param: SuccessAppointmentDialogView.Param,
    private val getAppointmentByIdWithParticipantUseCase: GetAppointmentByIdWithParticipantUseCase
) :
    BaseViewModel<SuccessAppointmentDialogState, BaseSideEffect>() {
    override val container = container<SuccessAppointmentDialogState, BaseSideEffect>(
        initialState = SuccessAppointmentDialogState(
        ),
    )
    {

    }

    init {
        loadAppointment()
    }

    private fun loadAppointment() = intent {
        getAppointmentByIdWithParticipantUseCase(param.appointmentId)
            .await()
            .processResult({ result ->
                reduce {
                    state.copy(
                        doctorName = result.appointmentParticipant?.lastName,
                        dateTime = result.appointmentModel.startDate.toKotlinLocalDateTime()
                    )
                }
            }, { processError(it) })
    }

    fun close() = intent {
        coordinatorRouter.sendEvent(SuccessAppointmentDialogNavigationEvents.Exit)
    }

    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) {
    }

    override fun onBack() {
        coordinatorRouter.sendEvent(CoordinatorEvent.Back)
    }
}

