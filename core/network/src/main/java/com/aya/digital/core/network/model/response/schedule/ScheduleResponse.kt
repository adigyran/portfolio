package com.aya.digital.core.network.model.response.schedule

import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDateTime

@JsonClass(generateAdapter = true)
data class Schedule(
    val id: Int,
    val scheduleId: String?,
    val active: Boolean?,
    val specialtyId: Int?,
    val practitionerId: Int?,
    val slots: List<Slot>?,
    val periodStart: LocalDateTime?,
    val periodEnd: LocalDateTime?
)
@JsonClass(generateAdapter = true)
data class Slot(
    val id: Int,
    val scheduleId: Int?,
    val slotStart: LocalDateTime,
    val slotEnd: LocalDateTime,
    val status: String?,
    val appointmentType: String?
)