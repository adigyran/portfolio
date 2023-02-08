package com.aya.digital.core.data.doctors

import kotlinx.datetime.LocalDate

data class DoctorData(
    val addressLine1: String?,
    val addressLine2: String?,
    val city: String?,
    val dateOfBirth: LocalDate?,
    val emailAddress: String?,
    val firstName: String?,
    val id: Int?,
    val lastName: String?,
    val middleName: String?,
    val mobilePhoneNumber: String?,
    val phoneNumber: String?,
    val photo: String?,
    val primaryFacilityName: Any?,
    val about: String?,
    val education: String?,
    val sex: String?,
    val speciality: DoctorSpeciality?,
    val state: String?,
    val zip: String?,
    val location: Location?,
)

data class DoctorSpeciality(
    val id: Int,
    val code: String,
    val name: String,
)