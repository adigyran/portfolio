package com.aya.digital.core.domain.appointment.participants.model

import com.aya.digital.core.data.doctors.DoctorData
import com.aya.digital.core.domain.base.models.doctors.DoctorModel

data class AppointmentDoctorParticipant(
    override val id: Int,
    override val avatarPhotoLink: String?,
    override val firstName: String,
    override val lastName: String,
    override val middleName: String?,
    val doctorModel: DoctorModel
) : AppointmentParticipant {


}

fun DoctorModel.mapToDoctorParticipant() = AppointmentDoctorParticipant(
    id = id,
    avatarPhotoLink = avatarPhotoLink,
    firstName = firstName,
    lastName = lastName,
    middleName = middleName,
    doctorModel = this
)