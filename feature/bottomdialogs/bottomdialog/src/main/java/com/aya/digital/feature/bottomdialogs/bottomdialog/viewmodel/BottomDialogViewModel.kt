package com.aya.digital.feature.bottomdialogs.bottomdialog.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.bottomdialogs.bottomdialog.viewmodel.BottomDialogState
import org.orbitmvi.orbit.viewmodel.container

class BottomDialogViewModel(val coordinatorRouter: CoordinatorRouter) :
    BaseViewModel<BottomDialogState, BaseSideEffect>() {
    override fun postErrorSideEffect(errorSideEffect: ErrorSideEffect) {
        TODO("Not yet implemented")
    }

    override val container = container<BottomDialogState, BaseSideEffect>(
        initialState = BottomDialogState,
    )
    {

    }
}

