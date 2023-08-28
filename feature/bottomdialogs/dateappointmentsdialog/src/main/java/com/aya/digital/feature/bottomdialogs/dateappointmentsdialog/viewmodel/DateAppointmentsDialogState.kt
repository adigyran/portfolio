package com.aya.digital.feature.bottomdialogs.dateappointmentsdialog.viewmodel

import android.os.Parcelable
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.mvi.BaseState

import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalDateTime

@Parcelize
data class DateAppointmentsDialogState(
    val dateTime: LocalDateTime,
    val appointments: List<AppointmentData>? = null
) : BaseState

@Parcelize
data class AppointmentData(
    val id:Int,
    val startDate: LocalDateTime,
    val isTelemed:Boolean = false,
    var doctor:AppointmentDoctor? = null,
    var patient: AppointmentPatient? = null,
    val status: AppointmentModel.AppointmentStatus
): Parcelable

@Parcelize
data class AppointmentDoctor(
    val id:Int,
    val doctorFirstName: String? = null,
    val doctorLastName: String? = null,
    val doctorMiddleName: String? = null,
    val doctorClinics: List<DoctorModel.ClinicModel>? = null,
    val doctorSpecialities: List<DoctorModel.SpecialityModel>? = null,
    val doctorAvatarImageLink:String? = null
): Parcelable

@Parcelize
data class AppointmentPatient(
    val id:Int,
    val firstName: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
    val birthDay:LocalDate? = null,
    val patientAvatarImageLink:String? = null
): Parcelable