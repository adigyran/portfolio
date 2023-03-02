package com.aya.digital.feature.tabs.home.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class HomeTabNavigationEvents : CoordinatorEvent() {
    object OpenDefaultScreen : HomeTabNavigationEvents()
    object ResetState : HomeTabNavigationEvents()
    object Finish : HomeTabNavigationEvents()
}