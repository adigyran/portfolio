package com.aya.digital.feature.tabs.profile.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class ProfileTabNavigationEvents : CoordinatorEvent() {
    object OpenDefaultScreen : ProfileTabNavigationEvents()
    object ResetState : ProfileTabNavigationEvents()
    object Finish : ProfileTabNavigationEvents()
}