package com.aya.digital.core.ui.base.utils

import android.os.Parcel
import android.os.Parcelable
import com.google.android.material.datepicker.CalendarConstraints.DateValidator
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isDistantPast
import kotlinx.datetime.minus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import kotlin.math.max

@Parcelize
class DateValidatorBirthday : DateValidator, Parcelable {

    override fun isValid(date: Long): Boolean {
        val toLocalDateTime =
            Instant.fromEpochMilliseconds(date).toLocalDateTime(TimeZone.currentSystemDefault())

        val currentNow = Clock.System.now()
        val maxDate = currentNow.minus(1, DateTimeUnit.DAY, TimeZone.currentSystemDefault()).toLocalDateTime(
            TimeZone.currentSystemDefault())
        return toLocalDateTime.toJavaLocalDateTime().isBefore(maxDate.toJavaLocalDateTime())
    }


}