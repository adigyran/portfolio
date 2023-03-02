package com.aya.digital.core.network.model.response.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationResponse(
    @Json( name = "message")
    val message: String?
)
