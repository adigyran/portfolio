package com.aya.digital.core.domain.schedule.base.model

import android.os.Parcelable
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ScheduleSlotModel(
    val id: Int,
    val startDate: @RawValue LocalDateTime,
    val endDate: @RawValue LocalDateTime,
    val overBooked: Boolean,
    val status: String,
    val comment: String,
    val type: SlotModelType
) : Parcelable
