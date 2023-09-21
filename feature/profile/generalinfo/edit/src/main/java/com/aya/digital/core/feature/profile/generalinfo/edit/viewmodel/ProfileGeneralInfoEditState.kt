package com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel

import android.net.Uri
import android.os.Parcelable
import com.aya.digital.core.domain.profile.generalinfo.edit.model.FlavoredProfileEditModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.FlavoredProfileModel
import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ProfileGeneralInfoEditState(
    val firstName: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
    val dateOfBirth: LocalDate? = null,
    val patientFields: PatientFields? = null,
    val doctorFields: DoctorFields? = null,
    val avatarUri:Uri? = null,
    val avatarUrl:String = ""
) : BaseState


@Parcelize
data class PatientFields(
    var sex: ProfileSex? = null,
    var height: String? = null,
    var heightError: String? = null,
    var weight: String? = null,
    var weightError: String? = null,
    var shortAddress: String? = null,
    var ssnOrTin: String? = null,
    var ssnOrTinError: String? = null,
): Parcelable

@Parcelize
data class DoctorFields(
    var npi: String? = null,
    var npiError:String? = null,
    var tin: String? = null,
    var tinError:String? = null,
    var licenseNumber: String? = null,
    var licenseNumberError:String? = null,
    var languages: List<FlavoredProfileModel.DoctorProfileModel.Language>? = null,
    var languagesError:String? = null,
    var specialities:List<FlavoredProfileModel.DoctorProfileModel.Speciality>? = null,
    var specialitiesError:String? = null,
    var medicalDegrees:List<FlavoredProfileModel.DoctorProfileModel.Degree>? = null,
    var medicalDegreesError:String? = null,
    var bio: String? = null,
    var bioError: String? = null,
): Parcelable