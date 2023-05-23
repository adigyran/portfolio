package com.aya.digital.core.ui.delegates.appointments.patientappointment.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import java.time.LocalDate

data class PatientEmptyAppointmentUIModel(
    val id: Int,
    val date: String
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is PatientEmptyAppointmentUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is PatientEmptyAppointmentUIModel
                && newItem.id == this.id
                && newItem.date == this.date
}