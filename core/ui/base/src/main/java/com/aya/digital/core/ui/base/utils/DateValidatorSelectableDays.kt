package com.aya.digital.core.ui.base.utils

import android.os.Parcel
import android.os.Parcelable
import com.google.android.material.datepicker.CalendarConstraints.DateValidator
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toKotlinLocalDate
import kotlinx.datetime.toLocalDateTime
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class DateValidatorSelectableDays(private val selectableDays: List<java.time.LocalDate>) : DateValidator, Parcelable {

    override fun isValid(date: Long): Boolean {
        val toLocalDateTime =
            Instant.fromEpochMilliseconds(date).toLocalDateTime(TimeZone.currentSystemDefault())

        return selectableDays.any { localDate -> localDate.toKotlinLocalDate()==toLocalDateTime.date }
    }


}