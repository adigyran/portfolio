package com.aya.digital.core.ui.delegates.appointments.patientappointment.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class PatientAppointmentMoreUIModel(
    val status: AppointmentUiStatus
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is PatientAppointmentMoreUIModel
                && newItem.status == this.status

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is PatientAppointmentMoreUIModel
                && newItem.status == this.status
}