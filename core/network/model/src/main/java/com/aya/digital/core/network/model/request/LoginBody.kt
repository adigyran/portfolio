package com.aya.digital.core.network.model.request

import com.squareup.moshi.Json

data class LoginBody(
    @Json(name = "login")
    val login: String,
    @Json(name = "password")
    val password: String
)
