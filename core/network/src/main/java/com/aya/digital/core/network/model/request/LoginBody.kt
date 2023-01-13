package com.aya.digital.core.network.model.request

import com.squareup.moshi.Json


data class LoginBody(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String
)
