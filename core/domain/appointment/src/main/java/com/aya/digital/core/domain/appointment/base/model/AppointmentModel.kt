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
    val participantName:String,
    val minutesDuration: Int?,
    val type:String
) : Parcelable

internal fun Appointment.toAppointmentModel() =
    AppointmentModel(
        id = id,
        comment = this.comment,
        createdAt = this.createdAt,
        startDate = this.startDate,
        endDate = this.endDate,
        minutesDuration = minutesDuration,
        participantName = participant?.let { "%s %s".format(it.firstName?:"",it.lastName?:"") }?:"",
        type = type ?:""
    )