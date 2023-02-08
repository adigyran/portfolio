package com.aya.digital.core.data.appointment

import kotlinx.datetime.LocalDateTime

data class AppoinmentSlot(
    val id: Int?,
    val scheduleId: Int,
    val slotStart: LocalDateTime,
    val slotEnd: LocalDateTime,
    val type: String?,
    val comment: String?,
    val status: String,
    val date: LocalDateTime
)