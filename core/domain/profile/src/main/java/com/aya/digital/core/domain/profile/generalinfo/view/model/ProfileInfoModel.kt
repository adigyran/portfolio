package com.aya.digital.core.domain.profile.generalinfo.view.model

import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.model.ProfileSex
import java.time.LocalDate

data class ProfileInfoModel(
    val id: Int,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val middleName: String?,
    val avatar: String?,
    val dateOfBirth: LocalDate?,
    val sex: ProfileSex?,
    val height: String?,
    val weight: String?,
    val shortAddress: String?
) {

}

fun CurrentProfile.mapToBriefProfile() = BriefProfileModel(
    this.id,
    this.email ?: "",
    this.firstName ?: "",
    this.lastName ?: "",
    this.middleName ?: "",
    this.avatar?.fullUrl
)
