package com.aya.digital.core.feature.appointments.appointmentcard.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime


sealed class AppointmentCardNavigationEvents : CoordinatorEvent() {
    data class CreateAppointment(
        val requestCode: String,
        val doctorId:Int,
        val slotDateTime: LocalDateTime?,
        val date: LocalDate
    ) : AppointmentCardNavigationEvents()
    data class OpenVideoCall(val roomId:Int):AppointmentCardNavigationEvents()


}