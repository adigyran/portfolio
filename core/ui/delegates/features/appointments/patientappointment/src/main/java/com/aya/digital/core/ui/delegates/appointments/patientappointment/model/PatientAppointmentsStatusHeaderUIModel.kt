package com.aya.digital.core.ui.delegates.appointments.patientappointment.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class PatientAppointmentsStatusHeaderUIModel(
    val status: AppointmentUiStatus,
    val isEmpty:Boolean = false
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is PatientAppointmentsStatusHeaderUIModel
                && newItem.status == this.status

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is PatientAppointmentsStatusHeaderUIModel
                && newItem.status == this.status
                && newItem.isEmpty == this.isEmpty
}