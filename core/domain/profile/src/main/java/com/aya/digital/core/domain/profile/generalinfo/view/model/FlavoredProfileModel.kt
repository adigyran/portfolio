package com.aya.digital.core.domain.profile.generalinfo.view.model

import android.os.Parcelable
import com.aya.digital.core.data.dictionaries.LanguageModel
import com.aya.digital.core.data.dictionaries.MedicalDegreeModel
import com.aya.digital.core.data.dictionaries.SpecialityModel
import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.model.ProfileSex
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class FlavoredProfileModel : Parcelable {
    data class PatientProfileModel(
        val height: String?,
        val weight: String?,
        val sex: ProfileSex?,
        val shortAddress: String?,
        val ssn: String?,
        val tin: String?
    ) : FlavoredProfileModel() {

    }

    data class DoctorProfileModel(var bio: String?=null, var languages: List<Language>?=null, var degrees:List<Degree>?=null, var specialities:List<Speciality>?=null) :
        FlavoredProfileModel() {
        @Parcelize
        data class Language(val id: Int, val code: String, val name: String) : Parcelable

        @Parcelize
        data class Degree(val id: Int, val code: String, val name: String) : Parcelable

        @Parcelize
        data class Speciality(val id: Int, val code: String, val name: String) : Parcelable
    }
}



fun LanguageModel.toLanguage() =
    FlavoredProfileModel.DoctorProfileModel.Language(id = id, code = code, name = name)

fun MedicalDegreeModel.toMedicalDegree() =
    FlavoredProfileModel.DoctorProfileModel.Degree(id = id, code = code, name = name)

fun SpecialityModel.toSpeciality() =
    FlavoredProfileModel.DoctorProfileModel.Speciality(id = id, code = code, name = name)

fun CurrentProfile.mapToPatientProfile() = FlavoredProfileModel.PatientProfileModel(
    weight = this.weight,
    height = this.height,
    sex = this.sex?.let { ProfileSex.getSexByTag(it) },
    shortAddress = this.shortAddress,
    ssn = this.ssn,
    tin = this.tin
)
