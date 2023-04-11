package com.aya.digital.core.network.model.response.doctors

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DoctorDataResponse(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val photo: String?,
    val bio: String?,
    val city: String?,
    val address: String?,
    val postalCode: String?,
    val specialities: List<SpecialityResponse>?,
    val insurances: List<InsuranceResponse>?,
    val location: LocationResponse?,
    val clinics: List<ClinicResponse>?
)