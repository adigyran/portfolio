package com.aya.digital.core.feature.auth.signin.viewmodel


import com.aya.digital.core.feature.tabviews.home.viewmodel.HomeSideEffects
import com.aya.digital.core.feature.tabviews.home.viewmodel.HomeState
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.viewmodel.container

class HomeViewModel(
    private val coordinatorRouter: CoordinatorRouter
) :
    BaseViewModel<HomeState, HomeSideEffects>() {
    override val container = container<HomeState, HomeSideEffects>(
        initialState = HomeState(""),
    )
    {

    }


}

