package com.aya.digital.core.network.model.request

import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDateTime

@JsonClass(generateAdapter = true)
data class SlotBody (
    val id: Int? = null,
    val practitioner_id: Int? = null,
    val scheduleId: Int? = null,
    val type: String? = null,
    val slotStart: LocalDateTime? = null,
    val slotEnd: LocalDateTime? = null,
    val comment: String? = null,
    val status: String? = null,
    val date: LocalDateTime? = null,
)