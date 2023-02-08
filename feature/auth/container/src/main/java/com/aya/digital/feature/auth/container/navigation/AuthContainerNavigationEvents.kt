package com.aya.digital.feature.auth.container.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class AuthContainerNavigationEvents : CoordinatorEvent() {
    object OpenDefaultScreen : CoordinatorEvent()
}