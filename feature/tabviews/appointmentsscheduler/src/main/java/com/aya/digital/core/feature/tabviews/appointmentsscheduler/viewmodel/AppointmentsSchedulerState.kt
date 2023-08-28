package com.aya.digital.core.feature.tabviews.appointmentsscheduler.viewmodel

import android.os.Parcelable
import com.aya.digital.core.domain.appointment.base.model.AppointmentWithParticipantModel
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import com.aya.digital.core.domain.base.models.appointment.AppointmentType
import com.aya.digital.core.domain.schedule.base.model.SlotModelType
import com.aya.digital.core.domain.schedule.doctor.scheduler.model.ScheduleDayModel
import com.aya.digital.core.domain.schedule.doctor.scheduler.model.SchedulerSlotModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth

@Parcelize
data class AppointmentsSchedulerState(
    val currentMonth: YearMonth? = null,
    val currentDay: LocalDate? = null,
    val doctorName:String? = null,
    val selectedDay: LocalDate? = null,
    val schedulerDays: List<SchedulerDay>? = null,
    val schedulerDaysWithStatus: List<SchedulerStatusDay>? = null,
    val schedulerSlots: List<SchedulerSlot>? = null,
    val appointments: List<AppointmentData>? = null,
    val expandedStatuses: Set<AppointmentModel.AppointmentStatus> = setOf(),
) : BaseState

@Parcelize
data class AppointmentData(
    val id: Int,
    val slotId: Int,
    val startDate: LocalDateTime,
    val status: AppointmentModel.AppointmentStatus = AppointmentModel.AppointmentStatus.SCHEDULED,
    val isTelemed: Boolean = false,
    val patient: AppointmentPatient?
) : Parcelable


@Parcelize
data class AppointmentPatient(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val avatarImageLink: String? = null
) : Parcelable

fun AppointmentWithParticipantModel.toAppointmentData() = AppointmentData(
    id = appointmentModel.id,
    slotId = appointmentModel.slotId ?: -1,
    startDate = appointmentModel.startDate,
    status = appointmentModel.status,
    isTelemed = appointmentModel.type is AppointmentType.Online,
    patient = appointmentParticipant?.run {
        AppointmentPatient(
            id = id,
            firstName = firstName,
            lastName = lastName,
            middleName = middleName,
            avatarImageLink = avatarPhotoLink
        )
    }
)

@Parcelize
data class SchedulerDay(
    val day: LocalDate
) : Parcelable

fun ScheduleDayModel.toSchedulerDay() = SchedulerDay(day = date)

@Parcelize
data class SchedulerSlot(
    val id: Int,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val type: SlotModelType
) : Parcelable

{
}

fun SchedulerSlotModel.toSchedulerSlot() =
    SchedulerSlot(id = id, startDateTime = startDateTime,endDateTime = endDateTime, type = type)

@Parcelize
data class SchedulerStatusDay(
    val day: LocalDate,
    val status: DayStatus
) : Parcelable


@Parcelize
sealed class DayStatus : Parcelable {
    data class Active(val hasAppointments: Boolean) : DayStatus()
    object Empty : DayStatus()
}

fun AppointmentsSchedulerState.getAppointmentsForSlot(slotId: Int) =
    appointments?.filter { it.slotId == slotId }

fun AppointmentsSchedulerState.getFirstAppointmentForSlot(slotId: Int) =
    appointments?.firstOrNull { it.slotId == slotId }

fun AppointmentsSchedulerState.hasAppointmentsForSlot(slotId: Int) =
    appointments?.any { it.slotId == slotId } ?: false

fun AppointmentsSchedulerState.getAppointmentsCountForSlot(slotId: Int) =
    appointments?.filter { it.slotId == slotId }?.size ?: 0