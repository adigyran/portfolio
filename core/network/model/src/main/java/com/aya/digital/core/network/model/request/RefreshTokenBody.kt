package com.aya.digital.core.network.model.request

import com.squareup.moshi.Json


data class RefreshTokenBody(
    @Json(name = "client_id")
    val clientId: String,
    @Json(name = "grant_type")
    val grantType: String,
    @Json(name = "client_secret")
    val clientSecret: String,
    @Json(name = "refresh_token")
    val refreshToken: String,
)
