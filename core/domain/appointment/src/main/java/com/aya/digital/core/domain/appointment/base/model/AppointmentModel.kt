package com.aya.digital.core.domain.appointment.base.model

import android.os.Parcelable
import com.aya.digital.core.data.appointment.Appointment
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

internal fun Appointment.toAppointmentModel() =
    AppointmentModel(
        id = this.id,
        comment = this.comment,
        createdAt = this.createdAt,
        startDate = this.startDate,
        endDate = this.endDate,
        minutesDuration = this.minutesDuration
    )