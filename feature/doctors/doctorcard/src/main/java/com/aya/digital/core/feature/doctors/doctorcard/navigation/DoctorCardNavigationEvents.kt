package com.aya.digital.core.feature.doctors.doctorcard.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime


sealed class DoctorCardNavigationEvents : CoordinatorEvent() {
    data class CreateAppointment(
        val requestCode: String,
        val doctorId:Int,
        val slotDateTime: LocalDateTime?,
        val date: LocalDate
    ) : DoctorCardNavigationEvents()

    data class OpenSuccessAppointmentCreation(val requestCode: String,val appointmentId:Int):DoctorCardNavigationEvents()
}