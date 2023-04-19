package com.aya.digital.feature.bottomdialogs.createappointmentdialog.viewmodel

import com.aya.digital.core.data.base.result.models.code.CodeResultModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.navigation.CreateAppointmentDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.ui.CreateAppointmentDialogView
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CreateAppointmentDialogViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val param: CreateAppointmentDialogView.Param

) :
    BaseViewModel<CreateAppointmentDialogState, BaseSideEffect>() {
    override val container = container<CreateAppointmentDialogState, BaseSideEffect>(
        initialState = CreateAppointmentDialogState(
        ),
    )
    {

    }

    fun close() = intent {
        coordinatorRouter.sendEvent(CreateAppointmentDialogNavigationEvents.Exit)
    }



    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) {
    }
}

