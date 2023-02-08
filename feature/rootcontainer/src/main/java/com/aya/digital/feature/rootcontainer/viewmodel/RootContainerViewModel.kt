package com.aya.digital.feature.rootcontainer.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.rootcontainer.navigation.RootContainerNavigationEvents
import com.aya.digital.feature.rootcontainer.ui.RootView
import org.orbitmvi.orbit.viewmodel.container

class RootContainerViewModel(private val coordinatorRouter: CoordinatorRouter) : BaseViewModel<RootContainerState, BaseSideEffect>() {
    fun openDefaultScreen() {
        coordinatorRouter.sendEvent(RootContainerNavigationEvents.OpenDefaultScreen)
    }

    override val container = container<RootContainerState, BaseSideEffect>(
        initialState = RootContainerState,
    )
    {

    }


}
