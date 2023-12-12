package com.aya.digital.core.domain.appointment.base.ext

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

internal fun LocalDate?.calculateDaysInterval(days: Long?) = DateTimeInterval.createInterval(
    date = this,
    days = days
)

internal data class DateTimeInterval(val startDateTime: LocalDateTime, val endDateTime: LocalDateTime) {

    companion object {
        fun createInterval(date: LocalDate?, days: Long?): DateTimeInterval {
            val systemTZ = ZoneId.systemDefault()
            val currentInstant = Instant.now()
            val currentDate = date ?: currentInstant.atZone(systemTZ).toLocalDate()
            val startDateTime = currentDate.atStartOfDay()
            val additionalDays = days ?: 0
            val endDateTime = currentDate.plusDays(additionalDays).atTime(23, 59, 59)
            return DateTimeInterval(startDateTime, endDateTime)
        }
    }
}