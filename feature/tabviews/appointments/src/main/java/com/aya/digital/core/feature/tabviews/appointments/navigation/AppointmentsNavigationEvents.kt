package com.aya.digital.core.feature.tabviews.appointments.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

sealed class AppointmentsNavigationEvents : CoordinatorEvent() {
    data class OpenVideoCall(val roomId:Int):AppointmentsNavigationEvents()
}