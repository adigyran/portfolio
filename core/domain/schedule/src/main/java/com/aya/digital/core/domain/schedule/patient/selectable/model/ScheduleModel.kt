package com.aya.digital.core.domain.schedule.patient.selectable.model

import android.os.Parcelable
import com.aya.digital.core.data.schedule.Schedule
import kotlinx.datetime.LocalDate
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ScheduleModel(
    val id: Int,
    val date: @RawValue LocalDate
) : Parcelable

internal fun Schedule.toScheduleModel() =
    ScheduleModel(
        id = id,
        date = dateTime.date
    )