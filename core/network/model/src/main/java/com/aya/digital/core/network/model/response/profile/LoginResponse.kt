package com.aya.digital.core.network.model.response.profile

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json( name = "id")
    val id: Int?,
    @Json( name = "email")
    val email: String?,
    @Json( name = "token")
    val token: String?,
)
