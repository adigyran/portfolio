package com.aya.digital.core.data.schedule

import kotlinx.datetime.LocalDateTime

data class ScheduleSlot(
    val id: Int,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val statusSlot: String,
    val commentSlot: String,
    val overBooked:Boolean,
    val type:String
)