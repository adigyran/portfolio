package com.aya.digital.core.feature.videocall.videocallscreen.viewmodel

import android.os.Parcelable
import com.aya.digital.core.domain.base.models.doctors.DoctorModel
import com.aya.digital.core.domain.base.models.patients.PatientModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize
import java.time.Duration
import java.time.Instant
import java.time.LocalDate

@Parcelize
data class VideoCallScreenState(
    val roomToken:String?=null,
    val callState: CallState = CallState.Initial,
    val participantState: ParticipantState = ParticipantState.ParticipantNotConnected,
    val participantConnectedStatus:Boolean = false,
    val participantFirstName:String?=null,
    val participantLastName:String?=null,
    val localVideoEnabled:Boolean = false,
    val localAudioEnabled:Boolean = false,
    val doctorData: DoctorData? = null,
    val patientData:PatientData? = null,
    val pipMode:Boolean = false,
    val videoCallDuration:Duration=Duration.ofSeconds(0L),
    val lastInstant:Instant=Instant.now(),
    val firstInstant:Instant= Instant.now()
) : BaseState

@Parcelize
data class DoctorData(
    val doctorFirstName: String? = null,
    val doctorLastName: String? = null,
    val doctorMiddleName: String? = null,
    val doctorAddress: String? = null,
    val doctorClinics: List<DoctorModel.ClinicModel>? = null,
    val doctorSpecialities: List<DoctorModel.SpecialityModel>? = null,
    val doctorInsurances:List<DoctorModel.InsuranceModel>? = null
) : Parcelable

@Parcelize
data class PatientData(
    val patientBirthDate: LocalDate? = null,
    val patientFirstName:String?=null,
    val patientLastName:String?=null,
    val patientInsurances:List<PatientModel.InsuranceModel>? = null
) : Parcelable
