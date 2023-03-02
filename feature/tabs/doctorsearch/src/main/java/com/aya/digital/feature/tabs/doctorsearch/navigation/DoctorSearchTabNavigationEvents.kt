package com.aya.digital.feature.tabs.doctorsearch.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class DoctorSearchTabNavigationEvents : CoordinatorEvent() {
    object OpenDefaultScreen : DoctorSearchTabNavigationEvents()
    object ResetState : CoordinatorEvent()
    object Finish : CoordinatorEvent()
}