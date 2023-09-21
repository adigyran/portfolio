package com.aya.digital.core.feature.profile.generalinfo.view.viewmodel

import android.os.Parcelable
import com.aya.digital.core.domain.profile.generalinfo.view.model.FlavoredProfileModel
import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ProfileGeneralInfoViewState(
    val avatar: String = "",
    val email:String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
    val dateOfBirth: LocalDate? = null,
    val patientFields: PatientFields? = null,
    val doctorFields: DoctorFields? = null

) : BaseState, Parcelable

@Parcelize
data class PatientFields(
    val ssn: String? = null,
    val tin: String? = null,
    val sex: ProfileSex? = null,
    val height: String? = null,
    val weight: String? = null,
    val shortAddress: String? = null
):Parcelable

@Parcelize
data class DoctorFields(
    val npi: String? = null,
    val tin: String? = null,
    val licenseNumber: String? = null,
    val languages: List<FlavoredProfileModel.DoctorProfileModel.Language>? = null,
    var specialities:List<FlavoredProfileModel.DoctorProfileModel.Speciality>? = null,
    var medicalDegrees:List<FlavoredProfileModel.DoctorProfileModel.Degree>? = null,
    val bio: String? = null,
):Parcelable

