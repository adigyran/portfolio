package com.aya.digital.core.domain.profile.generalinfo.view.model

import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.model.ProfileSex
import java.time.LocalDate

data class BriefProfileModel(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val avatar: String?
) {

}

fun CurrentProfile.mapToProfileInfo() = ProfileInfoModel(
    id = this.id,
    email = this.email,
    firstName = this.firstName,
    lastName = this.lastName,
    middleName = this.middleName,
    avatar = this.avatar?.fullUrl,
    dateOfBirth = this.dateOfBirth?.let { LocalDate.now() },
    sex = this.sex?.let { ProfileSex.getSexByTag(it) },
    height = this.height,
    weight = this.weight,
    shortAddress = null
)



