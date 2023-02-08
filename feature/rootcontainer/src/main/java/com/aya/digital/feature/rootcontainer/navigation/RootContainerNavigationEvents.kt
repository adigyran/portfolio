package com.aya.digital.feature.rootcontainer.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class RootContainerNavigationEvents : CoordinatorEvent() {
    object OpenDefaultScreen : RootContainerNavigationEvents()
}