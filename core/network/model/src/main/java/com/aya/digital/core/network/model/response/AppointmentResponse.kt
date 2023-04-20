package com.aya.digital.core.network.model.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AppointmentResponse(
    val id: Int,
    val createdAt: String,
    val endDate: String,
    val minutesDurations: Int,
    val startDate: String,
)

@JsonClass(generateAdapter = true)
data class PatientResponse(
    val id: Int,
    val firstName: String?,
    val lastName: String?,
    val photo: String?
)

@JsonClass(generateAdapter = true)
data class PractitionerResponse(
    val id: Int,
    val firstName: String?,
    val lastName: String?,
    val photo: String?,
    val speciality: String?
)

