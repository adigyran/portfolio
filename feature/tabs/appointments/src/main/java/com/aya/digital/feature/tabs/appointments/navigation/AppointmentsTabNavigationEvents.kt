package com.aya.digital.feature.tabs.appointments.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class AppointmentsTabNavigationEvents : CoordinatorEvent() {
    object OpenDefaultScreen : AppointmentsTabNavigationEvents()
    object ResetState : AppointmentsTabNavigationEvents()
    object Finish : AppointmentsTabNavigationEvents()
}