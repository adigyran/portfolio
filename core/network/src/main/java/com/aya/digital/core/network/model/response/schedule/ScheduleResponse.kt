package com.aya.digital.core.network.model.response.schedule

import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDateTime

@JsonClass(generateAdapter = true)
data class ScheduleResponse(
    val id: Int,
    val scheduleId: String?,
    val active: Boolean?,
    val specialtyId: Int?,
    val practitionerId: Int?,
    val slots: List<SlotResponse>?,
    val periodStart: LocalDateTime?,
    val periodEnd: LocalDateTime?
)
@JsonClass(generateAdapter = true)
data class SlotResponse(
    val id: Int,
    val scheduleId: Int?,
    val slotStart: LocalDateTime,
    val slotEnd: LocalDateTime,
    val status: String?,
    val appointmentType: String?
)