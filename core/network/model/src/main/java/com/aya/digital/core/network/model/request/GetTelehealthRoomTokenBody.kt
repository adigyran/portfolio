package com.aya.digital.core.network.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetTelehealthRoomTokenBody(
    @Json(name = "room_name")
    val roomName: String,
)
