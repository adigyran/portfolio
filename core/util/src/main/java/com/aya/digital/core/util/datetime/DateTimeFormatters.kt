package com.aya.digital.core.util.datetime

import android.content.Context
import com.aya.digital.core.ext.strings
import com.aya.digital.core.util.R
import java.time.format.DateTimeFormatter
import java.util.*

internal class DateTimeFormatters(private val context: Context) {
    val dateFormatterYmd = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.ymd_format]).defaultLocale()
    }

    val dateFormatterBirthday = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.birthday_format])
                .defaultLocale()
    }

    val dateFormatterTimeSlot = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.time_slot_format])
                .defaultLocale()
    }

    val dateFormatterTimeSlotTitle = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.time_slot_title_format])
                .defaultLocale()
    }
    val dateFormatterAppointmentDate = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.appointment_date_format])
                .defaultLocale()
    }

    val dateFormatterAppointmentTime = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.appointment_time_format])
                .defaultLocale()
    }
    val dateFormatterAppointmentCardDateTime = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.appointment_card_datetime_format])
                .defaultLocale()
    }

    fun DateTimeFormatter.defaultLocale() = this.withLocale(Locale.US)

}