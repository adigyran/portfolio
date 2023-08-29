package com.aya.digital.core.domain.profile.generalinfo.view.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ProfileInfoModel(
    val id: Int,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val middleName: String?,
    val avatar: String?,
    val dateOfBirth: LocalDate?,
    var flavoredProfileModel: FlavoredProfileModel? = null
) : Parcelable


fun BriefProfileModel.mapToProfileInfo() = ProfileInfoModel(
    id = this.id,
    email = this.email,
    firstName = this.firstName,
    lastName = this.lastName,
    middleName = this.middleName,
    avatar = this.avatar,
    dateOfBirth = this.birthday
)


