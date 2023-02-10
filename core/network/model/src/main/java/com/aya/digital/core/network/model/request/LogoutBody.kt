package com.aya.digital.core.network.model.request

import com.squareup.moshi.Json


data class LogoutBody(
    @Json(name = "client_id")
    val clientId: String,
    @Json(name = "refresh_token")
    val grantType: String,
    @Json(name = "client_secret")
    val clientSecret: String,
    @Json(name = "refresh_token")
    val refreshToken: String,
)
