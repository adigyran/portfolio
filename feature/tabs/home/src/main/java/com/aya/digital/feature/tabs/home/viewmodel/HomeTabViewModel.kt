package com.aya.digital.feature.tabs.home.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.viewmodel.container

class HomeTabViewModel(val coordinatorRouter: CoordinatorRouter) :
    BaseViewModel<HomeTabState, BaseSideEffect>() {
    override fun onBack() {
        TODO("Not yet implemented")
    }

    override val container = container<HomeTabState, BaseSideEffect>(
        initialState = HomeTabState,
    )
    {

    }
}

