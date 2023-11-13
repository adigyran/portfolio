package com.aya.digital.core.network.model.response.telehealth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TelehealthWaitTimeResponse(
    val beforeTimeout: Long,
    val afterTimeout: Long,
    val globalAppointmentTimeout:Boolean
)
