package com.aya.digital.core.feature.tabviews.appointments.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import kotlinx.datetime.LocalDate

sealed class AppointmentsNavigationEvents : CoordinatorEvent() {
    object OpenDefaultScreen : AppointmentsNavigationEvents()

    data class OpenAppointment(val appointmentId: Int) : AppointmentsNavigationEvents()

    data class OpenAppointmentsForSpecificDate(val requestCode: String, val date: LocalDate) :
        AppointmentsNavigationEvents()

    data class OpenVideoCall(val roomId: Int) : AppointmentsNavigationEvents()
}