package com.aya.digital.core.data.model.schedule

import kotlinx.datetime.LocalDateTime

data class ScheduleSlot(
    val id: Int,
    val scheduleId: Int?,
    val slotStart: LocalDateTime,
    val slotEnd: LocalDateTime,
    val status: String?,
    val appointmentType: String?
)