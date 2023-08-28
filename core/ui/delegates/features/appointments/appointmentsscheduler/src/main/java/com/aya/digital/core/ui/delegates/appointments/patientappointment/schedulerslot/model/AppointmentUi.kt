package com.aya.digital.core.ui.delegates.appointments.patientappointment.schedulerslot.model

data class AppointmentUi(
    val id: Int,
    val patient: AppointmentUiPatient?,
    val status: AppointmentUiStatus,
    val isTelemedicine: Boolean
) {
    data class AppointmentUiPatient(val id: Int, val patientAvatar: String?, val name: String)
}