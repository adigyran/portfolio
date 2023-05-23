package com.aya.digital.core.ui.delegates.appointments.patientappointment.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class PatientAppointmentUIModel(
    val id: Int,
    val startDateTime: Pair<String, String>,
    val duration: String,
    val doctorName: String,
    val doctorSpeciality: String,
    val participantAvatarLink: String?,
    val status: AppointmentUiStatus,
    val isTelemed: Boolean = false,
) : DiffItem {


    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is PatientAppointmentUIModel
                && newItem.id == this.id

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is PatientAppointmentUIModel
                && newItem.id == this.id
                && newItem.startDateTime == this.startDateTime
                && newItem.duration == this.duration
                && newItem.doctorName == this.doctorName
                && newItem.doctorSpeciality == this.doctorSpeciality
                && newItem.participantAvatarLink == this.participantAvatarLink
                && newItem.isTelemed == this.isTelemed
                && newItem.status == this.status
}