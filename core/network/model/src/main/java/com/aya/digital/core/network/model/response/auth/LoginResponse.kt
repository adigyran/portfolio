package com.aya.digital.core.network.model.response.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json( name = "refreshToken")
    val refreshToken: String?,
    @Json( name = "token")
    val token: String?,
)
