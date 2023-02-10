package com.aya.digital.core.network.model.request

import com.squareup.moshi.Json


data class ResetPasswordBody(
    @Json(name = "password")
    val password: String,
    @Json(name = "confirm_password")
    val confirmPassword: String
)
