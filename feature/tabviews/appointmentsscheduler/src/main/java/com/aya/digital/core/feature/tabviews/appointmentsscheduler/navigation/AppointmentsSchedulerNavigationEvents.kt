package com.aya.digital.core.feature.tabviews.appointmentsscheduler.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import java.time.LocalDateTime

sealed class AppointmentsSchedulerNavigationEvents : CoordinatorEvent() {
    object OpenDefaultScreen : AppointmentsSchedulerNavigationEvents()

    data class OpenAppointment(val appointmentId: Int) : AppointmentsSchedulerNavigationEvents()

    data class OpenAppointmentsForSpecificSlot(
        val requestCode: String,
        val startDateTime: LocalDateTime,
        val endDateTime: LocalDateTime
    ) :
        AppointmentsSchedulerNavigationEvents()

    data class OpenVideoCall(val roomId: Int) : AppointmentsSchedulerNavigationEvents()
}