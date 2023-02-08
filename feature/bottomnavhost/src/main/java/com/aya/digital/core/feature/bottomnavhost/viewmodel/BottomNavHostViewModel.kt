package com.aya.digital.core.feature.bottomnavhost.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.viewmodel.container

class BottomNavHostViewModel(val coordinatorRouter: CoordinatorRouter) :
    BaseViewModel<BottomNavHostState, BaseSideEffect>() {
    override val container = container<BottomNavHostState, BaseSideEffect>(
        initialState = BottomNavHostState,
    )
    {

    }
}