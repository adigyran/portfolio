package com.aya.digital.core.domain.appointment.participants.model

import com.aya.digital.core.data.doctors.PatientData
import com.aya.digital.core.domain.base.models.patients.PatientModel

data class AppointmentPatientParticipant(
    override val id: Int,
    override val avatarPhotoLink: String?,
    override val firstName: String,
    override val lastName: String,
    override val middleName: String?,
    val patientModel: PatientModel
) : AppointmentParticipant {

}

fun PatientModel.mapToPatientParticipant() = AppointmentPatientParticipant(
    id = id,
    avatarPhotoLink = avatarPhotoLink,
    firstName = firstName,
    lastName = lastName,
    middleName = middleName,
    patientModel = this
)