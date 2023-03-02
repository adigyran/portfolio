package com.aya.digital.core.feature.bottomnavhost.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class BottomNavHostNavigationEvents : CoordinatorEvent() {
    object OpenHome : BottomNavHostNavigationEvents()
    object OpenAppointments : BottomNavHostNavigationEvents()
    object OpenDoctorSearch : BottomNavHostNavigationEvents()
    object OpenProfile : BottomNavHostNavigationEvents()
    object Finish : BottomNavHostNavigationEvents()

}