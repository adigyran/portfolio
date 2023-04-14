package com.aya.digital.core.domain.schedule.base.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class ScheduleSlotModel(
    val id:Int,
    val startDate: LocalDateTime,
    val endDate:LocalDateTime,
    val overBooked:Boolean,
    val status:String,
    val comment:String
)
