package com.aya.digital.core.network.model.request

import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate

@JsonClass(generateAdapter = true)
data class ScheduleWithSlotsBody(
    val practitionerId: Int,
    val days: List<LocalDate>,
    val dayStart: String,
    val dayEnd: String,
    val active: Boolean,
    val type: String,
    val duration: Int
)