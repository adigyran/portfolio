package com.aya.digital.core.feature.auth.signin.viewmodel

import com.aya.digital.core.data.base.dataprocessing.dataloading.enums.OperationState
import com.aya.digital.core.feature.auth.signin.ui.RestorePasswordView
import com.aya.digital.core.feature.auth.signin.viewmodel.mode.RestorePasswordOperationState
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.viewmodel.container

class RestorePasswordViewModel(
    private val coordinatorRouter: CoordinatorRouter,
    private val param: RestorePasswordView.Param
) :
    BaseViewModel<RestorePasswordState, BaseSideEffect>() {
    override val container = container<RestorePasswordState, BaseSideEffect>(
        initialState = RestorePasswordState(operationState = param.operationState.getInitialOperationState()),
    )
    {

    }

    private fun RestorePasswordView.Param.RestorePasswordOperationStateParam.getInitialOperationState(): RestorePasswordOperationState =
        when(this)
        {
            RestorePasswordView.Param.RestorePasswordOperationStateParam.ChangeTempPass -> RestorePasswordOperationState.ChangeTempPassword
            RestorePasswordView.Param.RestorePasswordOperationStateParam.RestorePassword -> RestorePasswordOperationState.RestoringEmailInput
        }

}

