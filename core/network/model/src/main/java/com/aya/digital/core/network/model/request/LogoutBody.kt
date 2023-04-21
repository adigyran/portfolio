package com.aya.digital.core.network.model.request

import com.squareup.moshi.Json


data class LogoutBody(
    @Json(name = "refresh_token")
    val refreshToken: String
)
