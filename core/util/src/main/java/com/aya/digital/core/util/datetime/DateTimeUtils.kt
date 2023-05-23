package com.aya.digital.core.util.datetime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime


interface DateTimeUtils {
    fun parseIsoDate(date: String): LocalDate
    fun formatIsoDate(date: LocalDate):String
    fun parseYmdDate(date: String): LocalDate
    fun formatYmdDate(date: LocalDate): String
    fun formatBirthDate(date: LocalDate):String
    fun formatSlotTime(time: LocalTime):String
    fun formatSlotTitleDate(date:LocalDateTime):String
    fun formatAppointmentDateTime(dateTime: LocalDateTime):Pair<String,String>
    fun formatAppointmentCardDateTime(dateTime: LocalDateTime):String

}