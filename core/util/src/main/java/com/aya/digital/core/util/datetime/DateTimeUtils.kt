package com.aya.digital.core.util.datetime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlin.time.Duration


interface DateTimeUtils {
    fun parseIsoDate(date: String): LocalDate
    fun formatIsoDate(date: LocalDate):String
    fun parseYmdDate(date: String): LocalDate
    fun formatYmdDate(date: LocalDate): String
    fun formatBirthDate(date: LocalDate):String
    fun formatSlotTime(time: LocalTime):String
    fun format24HoursTime(time: LocalTime):String
    fun formatTimeZone(date: LocalDate):String
    fun formatSchedulerSlotTime(time: LocalTime):String
    fun formatSchedulerDateTime(date: LocalDate):String
    fun formatDayOfWeekName(date: LocalDate):String
    fun formatDayOfMonth(date: LocalDate):String
    fun formatSlotTitleDate(date:LocalDateTime):String
    fun formatAppointmentDateTime(dateTime: LocalDateTime):Pair<String,String>
    fun formatAppointmentCardDateTime(dateTime: LocalDateTime):String
    fun formatDurationMins(duration: Duration):String
    fun formatDurationMinSec(duration: java.time.Duration):String
}