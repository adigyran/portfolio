package com.aya.digital.core.ui.delegates.appointments.patientappointment.model

import com.aya.digital.core.ui.adapters.base.DiffItem
import java.time.LocalDate

data class PatientAppointmentUIModel(
    val id: Int,
    val startDate: String,
    val duration: String,
    val comment:String,
    val participantName:String,
    val isTelemed: Boolean = false
) : DiffItem {

    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is PatientAppointmentUIModel

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is PatientAppointmentUIModel
                && newItem.id == this.id
                && newItem.startDate == this.startDate
                && newItem.duration == this.duration
                && newItem.comment == this.comment
                && newItem.participantName == this.participantName
                && newItem.isTelemed == this.isTelemed
}