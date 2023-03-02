package com.aya.digital.feature.tabs.profile.viewmodel

import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.feature.tabs.profile.viewmodel.ProfileTabState
import org.orbitmvi.orbit.viewmodel.container

class ProfileTabViewModel(val coordinatorRouter: CoordinatorRouter) :
    BaseViewModel<ProfileTabState, BaseSideEffect>() {

    override val container = container<ProfileTabState, BaseSideEffect>(
        initialState = ProfileTabState,
    )
    {

    }
}

