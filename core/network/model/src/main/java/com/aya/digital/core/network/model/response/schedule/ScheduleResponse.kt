package com.aya.digital.core.network.model.response.schedule

import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDateTime

@JsonClass(generateAdapter = true)
data class ScheduleResponse(
    val id: Int = 0,
    val startDate:String
)