package com.aya.digital.core.feature.tabviews.profile.viewmodel

import com.aya.digital.core.feature.tabviews.profile.navigation.ProfileNavigationEvents
import com.aya.digital.core.feature.tabviews.profile.viewmodel.ProfileState
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import kotlinx.coroutines.rx3.await
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber

class ProfileViewModel(
    private val coordinatorRouter: CoordinatorRouter
) :
    BaseViewModel<ProfileState, BaseSideEffect>() {
    override val container = container<ProfileState, BaseSideEffect>(
        initialState = ProfileState(""),
    )
    {

    }


}

