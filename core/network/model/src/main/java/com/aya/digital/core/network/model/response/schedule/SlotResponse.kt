package com.aya.digital.core.network.model.response.schedule

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SlotResponse(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val statusSlot: String?,
    val commentSlot: String?,
    val overbooked:Boolean
)