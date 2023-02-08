package com.aya.digital.feature.auth.container.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.navigator.FragmentContainerGraph
import com.aya.digital.feature.auth.container.navigation.AuthContainerNavigationEvents
import org.orbitmvi.orbit.viewmodel.container

class AuthContainerViewModel(val coordinatorRouter: CoordinatorRouter) :
    BaseViewModel<AuthContainerState, BaseSideEffect>() {

    override val container = container<AuthContainerState, BaseSideEffect>(
        initialState = AuthContainerState,
    )
    {

    }
}

