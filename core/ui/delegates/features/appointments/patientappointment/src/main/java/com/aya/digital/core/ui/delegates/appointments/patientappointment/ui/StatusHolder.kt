package com.aya.digital.core.ui.delegates.appointments.patientappointment.ui

import com.aya.digital.core.ui.delegates.appointments.patientappointment.model.AppointmentUiStatus

interface StatusHolder {
    fun getDelegateStatus():AppointmentUiStatus
}