package com.aya.digital.core.domain.appointment.participants.model

sealed interface AppointmentParticipant
{
    val id:Int
    val avatarPhotoLink:String?
    val firstName: String
    val lastName: String
    val middleName: String?
}