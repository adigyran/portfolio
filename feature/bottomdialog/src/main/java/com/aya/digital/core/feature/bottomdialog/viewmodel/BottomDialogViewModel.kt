package com.aya.digital.core.feature.bottomdialog.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.viewmodel.container

class BottomDialogViewModel(val coordinatorRouter: CoordinatorRouter) :
    BaseViewModel<BottomDialogState, BaseSideEffect>() {
    override val container = container<BottomDialogState, BaseSideEffect>(
        initialState = BottomDialogState,
    )
    {

    }
}

