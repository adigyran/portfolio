package com.aya.digital.core.feature.tabviews.home.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class HomeNavigationEvents : CoordinatorEvent() {
    object FindDoctor:HomeNavigationEvents()
    object ShowAppointments:HomeNavigationEvents()
}