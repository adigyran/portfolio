package com.aya.digital.core.ui.delegates.appointments.patientappointment.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import java.time.LocalDate

data class PatientAppointmentUIModel(val id:Int, val startDate: String, val duration:String) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is PatientAppointmentUIModel
    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is PatientAppointmentUIModel && newItem.id == this.id
}