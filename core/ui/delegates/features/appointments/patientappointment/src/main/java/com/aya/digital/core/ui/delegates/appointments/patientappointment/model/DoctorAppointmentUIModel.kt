package com.aya.digital.core.ui.delegates.appointments.patientappointment.model

import com.aya.digital.core.ui.adapters.base.DiffItem

data class DoctorAppointmentUIModel(
    val id: Int,
    val startDateTime: Pair<String, String>,
    val duration: String,
    val name: String,
    val age:String,
    val participantAvatarLink: String?,
    val status: AppointmentUiStatus,
    val isTelemed: Boolean = false,
) : DiffItem {


    override fun areItemsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorAppointmentUIModel
                && newItem.id == this.id

    override fun areContentsTheSame(newItem: DiffItem): Boolean =
        newItem is DoctorAppointmentUIModel
                && newItem.id == this.id
                && newItem.startDateTime == this.startDateTime
                && newItem.duration == this.duration
                && newItem.name == this.name
                && newItem.age == this.age
                && newItem.participantAvatarLink == this.participantAvatarLink
                && newItem.isTelemed == this.isTelemed
                && newItem.status == this.status
}