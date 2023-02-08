package com.aya.digital.core.network.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate

@JsonClass(generateAdapter = true)
data class RegistrationBody(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
    @Json(name = "firstName") val firstName: String,
    @Json(name = "lastName") val lastName: String,
    @Json(name = "ssn") val ssn: String,
    @Json(name = "sex") val sex: String,
    @Json(name = "dateOfBirth") val dateOfBirth: LocalDate,
)
