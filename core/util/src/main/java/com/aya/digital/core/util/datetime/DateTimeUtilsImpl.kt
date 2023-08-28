package com.aya.digital.core.util.datetime

import android.content.Context
import kotlinx.datetime.*
import java.time.format.DateTimeFormatter
import kotlin.time.Duration

internal class DateTimeUtilsImpl(
    private val context: Context,
    private val formatters: DateTimeFormatters
) : DateTimeUtils {

    override fun parseIsoDate(date: String): LocalDate = LocalDate.parse(date)
    override fun formatIsoDate(date: LocalDate): String =
        date.toString()

    override fun parseYmdDate(date: String): LocalDate =
        date.parыe(formatters.dateFormatterYmd)

    override fun formatYmdDate(date: LocalDate): String =
        date.format(formatters.dateFormatterYmd)

    override fun formatBirthDate(date: LocalDate): String =
        date.format(formatters.dateFormatterBirthday)

    override fun formatSlotTime(time: LocalTime): String =
        time.format(formatters.dateFormatterTimeSlot)

    override fun formatSchedulerSlotTime(time: LocalTime): String = time.format(formatters.dateFormatterSchedulerTimeSlot)

    override fun formatSchedulerDateTime(date: LocalDate): String = date.format(formatters.dateFormatterSchedulerDate)

    override fun formatDayOfWeekName(date: LocalDate): String = date.format(formatters.dateFormatterDayOfWeek)

    override fun formatDayOfMonth(date: LocalDate): String = date.format(formatters.dateFormatterDayOfMonth)

    override fun formatSlotTitleDate(date: LocalDateTime): String =
        date.format(formatters.dateFormatterTimeSlotTitle)

    override fun formatAppointmentDateTime(dateTime: LocalDateTime): Pair<String, String> {
        val appointmentDate = dateTime.format(formatters.dateFormatterAppointmentDate)
        val appointmentTime = dateTime.format(formatters.dateFormatterAppointmentTime)
        return Pair(appointmentDate, appointmentTime)
    }

    /*    override fun formatAppointmentDateTime(dateTime: LocalDateTime): String  =
            dateTime.toJavaLocalDateTime().format(formatters.dateFormatterAppointmentDateTime.get())*/

    override fun formatAppointmentCardDateTime(dateTime: LocalDateTime): String =
        dateTime.format(formatters.dateFormatterAppointmentCardDateTime)

    override fun formatDurationMins(duration: Duration): String {
        TODO("Not yet implemented")
    }

    private fun LocalDateTime.format(formatter: ThreadLocal<DateTimeFormatter>) =
        this.toJavaLocalDateTime().format(formatter.get())

    private fun LocalTime.format(formatter: ThreadLocal<DateTimeFormatter>) =
        this.toJavaLocalTime().format(formatter.get())

    private fun LocalDate.format(formatter: ThreadLocal<DateTimeFormatter>) =
        this.toJavaLocalDate().format(formatter.get())

    private fun String.parыe(formatter: ThreadLocal<DateTimeFormatter>) =
        java.time.LocalDate.parse(this, formatter.get()).toKotlinLocalDate()

}