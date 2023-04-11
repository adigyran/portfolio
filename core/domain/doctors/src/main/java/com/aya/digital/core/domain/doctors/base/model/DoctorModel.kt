package com.aya.digital.core.domain.doctors.base.model

data class DoctorModel(
    val id: Int, val firstName: String,
    val lastName: String,
    val middleName: String?,
    val avatarPhotoLink: String?,
    val bio: String,
)
