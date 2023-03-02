package com.aya.digital.core.network.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate

@JsonClass(generateAdapter = true)
data class RegistrationBody(
    @Json(name = "login") val login: String,
    @Json(name = "firstName") val firstName: String,
    @Json(name = "lastName") val lastName: String,
    @Json(name = "email") val email: String,
    @Json(name = "phone") val phone: String?,
    @Json(name = "password") val password: String,
    @Json(name = "confirm_password") val confirmPassword: String,
    @Json(name = "insurance") val insurance: List<Int>,
)
