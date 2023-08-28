package com.aya.digital.core.domain.base.models.appointment

import android.os.Parcelable
import com.aya.digital.core.data.appointment.Appointment
import com.aya.digital.core.data.appointment.Status

import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class AppointmentModel(
    val id: Int,
    val comment: String?,
    val createdAt: LocalDateTime,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val participantId:Int?,
    val minutesDuration: Int?,
    val status: AppointmentStatus,
    val type: AppointmentType,
    val slotId:Int?
) : Parcelable {
    enum class AppointmentStatus {
        SCHEDULED,
        DONE,
        CANCELLED
    }
}


fun Appointment.toAppointmentModel() =
    AppointmentModel(
        id = id,
        comment = if (comment.isNullOrBlank()) null else comment,
        createdAt = createdAt,
        startDate = startDate,
        endDate = endDate,
        minutesDuration = minutesDuration,
        participantId = participant?.id,
        status = when (status) {
            Status.SCHEDULED -> AppointmentModel.AppointmentStatus.SCHEDULED
            Status.CANCELLED -> AppointmentModel.AppointmentStatus.CANCELLED
            Status.DONE -> AppointmentModel.AppointmentStatus.DONE
        },
        type = type?.let {
            if (it.contains(
                    "online",
                    ignoreCase = true
                )
            ) AppointmentType.Online(telemedPreTime) else AppointmentType.Offline
        } ?: AppointmentType.Offline,
        slotId = slotId
    )