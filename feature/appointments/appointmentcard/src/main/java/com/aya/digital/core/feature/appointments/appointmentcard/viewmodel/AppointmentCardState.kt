package com.aya.digital.core.feature.appointments.appointmentcard.viewmodel

import android.os.Parcelable
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.domain.base.models.patients.PatientModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
internal data class AppointmentCardState(
    val appointmentDate: @RawValue LocalDateTime? = null,
    val appointmentComment: String? = null,
    val isTelemed: Boolean? = null,
    val telemedPreTimeMins:Long? = null,
    val participantAvatar:String? = null,
    val doctorData: DoctorData? = null,
    val patientData:PatientData? = null,
    val commentReadMore:Boolean = false
) : BaseState

@Parcelize
internal data class DoctorData(
    val doctorFirstName: String? = null,
    val doctorLastName: String? = null,
    val doctorMiddleName: String? = null,
    val doctorAddress: String? = null,
    val doctorClinics: List<DoctorModel.ClinicModel>? = null,
    val doctorSpecialities: List<DoctorModel.SpecialityModel>? = null,
    val doctorInsurances:List<DoctorModel.InsuranceModel>? = null
) : Parcelable

@Parcelize
internal data class PatientData(
    val patientBirthDate: @RawValue LocalDate? = null,
    val patientFirstName:String?=null,
    val patientLastName:String?=null,
    val patientInsurances:List<PatientModel.InsuranceModel>? = null
) : Parcelable