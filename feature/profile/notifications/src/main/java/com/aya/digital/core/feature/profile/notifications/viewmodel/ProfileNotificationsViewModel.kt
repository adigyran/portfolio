package com.aya.digital.core.feature.profile.notifications.viewmodel

import com.aya.digital.core.feature.profile.notifications.FieldsTags
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.BaseViewModel
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfileNotificationsViewModel(
    private val coordinatorRouter: CoordinatorRouter
) :
    BaseViewModel<ProfileNotificationsState, BaseSideEffect>() {
    override val container = container<ProfileNotificationsState, BaseSideEffect>(
        initialState = ProfileNotificationsState(),
    )
    {

    }

    fun onEmailNotificationSwitched(value: Boolean) = intent {
        reduce { state.copy(emailNotification = value) }
    }

}

