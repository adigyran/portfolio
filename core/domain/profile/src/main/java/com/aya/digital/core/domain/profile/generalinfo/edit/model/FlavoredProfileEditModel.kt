package com.aya.digital.core.domain.profile.generalinfo.edit.model

import android.os.Parcelable
import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.model.ProfileSex
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class FlavoredProfileEditModel : Parcelable {
    data class PatientProfileEditModel(
        val height: String?,
        val weight: String?,
        val sex: ProfileSex?,
        val shortAddress: String?,
        val ssn:String?,
        val tin:String?
    ) : FlavoredProfileEditModel()
    {

    }

    data class DoctorProfileEditModel(val bio: String?, val languages: List<Language>?) : FlavoredProfileEditModel() {
        @Parcelize
        data class Language(val id: Int, val code: String, val name: String) : Parcelable
    }
}

fun CurrentProfile.mapToPatientProfile() = FlavoredProfileEditModel.PatientProfileEditModel(
    weight = this.weight,
    height = this.height,
    sex = this.sex?.let { ProfileSex.getSexByTag(it) },
    shortAddress = this.shortAddress,
    ssn = this.ssn,
    tin = this.tin
)
