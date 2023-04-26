package com.aya.digital.core.data.appointment

import kotlinx.datetime.LocalDateTime

data class Appointment(
    val id: Int,
    val comment: String?,
    val createdAt: LocalDateTime,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val minutesDuration: Int?,
    val type:String?
)