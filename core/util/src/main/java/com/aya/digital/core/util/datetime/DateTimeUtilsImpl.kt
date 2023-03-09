package com.aya.digital.core.util.datetime

import android.content.Context
import com.aya.digital.core.ext.strings
import com.aya.digital.core.util.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

internal class DateTimeUtilsImpl(private val context: Context) : DateTimeUtils {
    private val dateFormatterYmd = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.ymd_format]).defaultLocale()
    }

    private val dateFormatterBirthday = object : ThreadLocal<DateTimeFormatter>() {
        override fun initialValue(): DateTimeFormatter =
            DateTimeFormatter
                .ofPattern(context.strings[R.string.birthday_format])
                .defaultLocale()
    }


    override fun parseIsoDate(date: String): LocalDate = LocalDate.parse(date)
    override fun formatIsoDate(date: LocalDate): String =
        date.toString()

    override fun parseYmdDate(date: String): LocalDate =
        LocalDate.parse(date,dateFormatterYmd.get())

    override fun formatYmdDate(date: LocalDate): String =
        date.format(dateFormatterYmd.get())

    override fun formatBirthDate(date: LocalDate): String  =
        date.format(dateFormatterBirthday.get())


    private fun DateTimeFormatter.defaultLocale() = this.withLocale(Locale.US)
}