package com.aya.digital.core.domain.schedule.patient.selectable.model

import android.os.Parcelable
import com.aya.digital.core.data.schedule.Schedule
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ScheduleModel(
    val id: Int,
    val date: LocalDate
) : Parcelable

internal fun Schedule.toScheduleModel() =
    ScheduleModel(
        id = id,
        date = dateTime.toLocalDate()
    )