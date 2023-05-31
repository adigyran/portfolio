package com.aya.digital.core.feature.tabviews.appointments.viewmodel

import android.os.Parcelable
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.datetime.LocalDateTime
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class AppointmentsState(val appointments: List<AppointmentData>? = null) : BaseState

@Parcelize
data class AppointmentData(
    val id:Int,
    val startDate: @RawValue LocalDateTime,
    val status:AppointmentModel.AppointmentStatus = AppointmentModel.AppointmentStatus.SCHEDULED,
    val expandedStatuses:Set<ExpandedStatus> = setOf(),
    val isTelemed:Boolean = false,
    val doctor:AppointmentDoctor? = null
):Parcelable

@Parcelize
sealed class ExpandedStatus():Parcelable
{
    object Scheduled : ExpandedStatus()
    object Done : ExpandedStatus()
    object Cancelled:ExpandedStatus()
}

@Parcelize
data class AppointmentDoctor(
    val id:Int,
    val doctorFirstName: String? = null,
    val doctorLastName: String? = null,
    val doctorMiddleName: String? = null,
    val doctorClinics: List<DoctorModel.ClinicModel>? = null,
    val doctorSpecialities: List<DoctorModel.SpecialityModel>? = null,
    val doctorAvatarImageLink:String? = null
):Parcelable
