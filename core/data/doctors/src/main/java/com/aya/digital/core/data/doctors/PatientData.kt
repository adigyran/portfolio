package com.aya.digital.core.data.doctors

import kotlinx.datetime.LocalDate


data class PatientData(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val avatarPhotoLink:String?,
    val birthDate:LocalDate?,
    val insurances:List<Insurance>,
    )