package com.aya.digital.core.domain.appointment.base.model

import android.os.Parcelable
import kotlinx.datetime.LocalDateTime
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class AppointmentModel(
    val id: Int,
    val comment: String?,
    val createdAt: @RawValue LocalDateTime,
    val startDate: @RawValue LocalDateTime,
    val endDate: @RawValue LocalDateTime,
    val minutesDuration: Int?
) : Parcelable
