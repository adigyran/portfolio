package com.aya.digital.core.ui.delegates.appointments.patientappointment.model

import com.aya.digital.core.localisation.R

enum class AppointmentUiStatus(val nameId:Int) {
    SCHEDULED(R.string.appointment_status_scheduled_title),
    CANCELLED(R.string.appointment_status_cancelled_title),
    DONE(R.string.appointment_status_done_title)
}