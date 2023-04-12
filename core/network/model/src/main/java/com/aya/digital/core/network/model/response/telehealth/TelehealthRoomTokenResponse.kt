package com.aya.digital.core.network.model.response.telehealth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TelehealthRoomTokenResponse(
    val token: String,
)
