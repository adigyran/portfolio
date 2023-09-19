package com.aya.digital.feature.bottomdialogs.createscheduledialog.viewmodel

import android.os.Parcelable
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalDateTime

@Parcelize
data class CreateScheduleDialogState(
    val date: LocalDate,
    val isTelemed: Boolean = false,
    val startTime: LocalDateTime? = null,
    val startTimeError:String? = null,
    val endTime: LocalDateTime? = null,
    val endTimeError:String?=null,
    val selectedDuration: ScheduleSlotDuration? = null,
    val availableDurations: Set<ScheduleSlotDuration>? = null
) : BaseState

{
}

@Parcelize
data class ScheduleSlotDuration(val tag: String, val durationMinutes: Int) : Parcelable

fun LocalDateTime?.getDateTime(date:LocalDate) = this?:date.atStartOfDay()

