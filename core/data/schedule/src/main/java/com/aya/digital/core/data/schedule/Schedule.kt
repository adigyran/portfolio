package com.aya.digital.core.data.schedule

import kotlinx.datetime.LocalDateTime

data class Schedule(
    val id: Int,
    val scheduleId: String?,
    val active: Boolean?,
    val specialtyId: Int?,
    val practitionerId: Int?,
    val slots: List<ScheduleSlot>?,
    val periodStart: LocalDateTime?,
    val periodEnd: LocalDateTime?
)

