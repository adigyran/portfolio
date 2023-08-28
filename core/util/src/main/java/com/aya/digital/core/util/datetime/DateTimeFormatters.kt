package com.aya.digital.core.util.datetime

import android.content.Context
import androidx.core.os.ConfigurationCompat
import com.aya.digital.core.ext.strings
import com.aya.digital.core.util.R
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.Locale


internal class DateTimeFormatters(private val context: Context) {
    val dateFormatterYmd = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.ymd_format]).currentLocale()
    }

    val dateFormatterBirthday = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.birthday_format])
                .currentLocale()
    }

    val dateFormatterTimeSlot = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.time_slot_format])
                .currentLocale()
    }

    val dateFormatterTimeSlotTitle = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.time_slot_title_format])
                .currentLocale()
    }
    val dateFormatterAppointmentDate = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.appointment_date_format])
                .currentLocale()
    }

    val dateFormatterAppointmentTime = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.appointment_time_format])
                .currentLocale()
    }
    val dateFormatterAppointmentCardDateTime = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.appointment_card_datetime_format])
                .currentLocale()
    }

    val dateFormatterDayOfWeek = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.day_of_week_format])
                .currentLocale()
    }

    val dateFormatterDayOfMonth = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.day_of_month_format])
                .currentLocale()
    }

    val dateFormatterSchedulerTimeSlot = object : ThreadLocal<DateTimeFormatter>() {
        val ampm: MutableMap<Long, String> = HashMap()
        init {

            ampm[0L] = "am"
            ampm[1L] = "pm"
        }
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatterBuilder()
                .appendPattern(context.strings[R.string.scheduler_time_slot_format])
                .appendText(ChronoField.AMPM_OF_DAY, ampm)
                .toFormatterCurrentLocale()
    }

    val dateFormatterSchedulerDate = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.scheduler_date_format])
                .currentLocale()
    }



    private fun getCurrentLocale() = ConfigurationCompat.getLocales(context.resources.configuration).get(0)?: Locale.getDefault()
    private fun DateTimeFormatter.currentLocale(): DateTimeFormatter = this.withLocale(getCurrentLocale())
    private fun DateTimeFormatterBuilder.toFormatterCurrentLocale():DateTimeFormatter = this.toFormatter().currentLocale()



}