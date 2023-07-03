package com.aya.digital.core.feature.doctors.doctorsfiltersview.navigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime


sealed class DoctorsFiltersViewNavigationEvents : CoordinatorEvent() {
    data class CreateAppointment(
        val requestCode: String,
        val doctorId:Int,
        val slotDateTime: LocalDateTime?,
        val date: LocalDate
    ) : DoctorsFiltersViewNavigationEvents()

    data class OpenSuccessAppointmentCreation(val requestCode: String,val appointmentId:Int):
        DoctorsFiltersViewNavigationEvents()
}