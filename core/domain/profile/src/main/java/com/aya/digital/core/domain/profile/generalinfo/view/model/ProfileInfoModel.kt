package com.aya.digital.core.domain.profile.generalinfo.view.model

import android.app.Activity
import android.os.Parcelable
import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.model.ProfileSex
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
    val sex: ProfileSex?,
    val height: String?,
    val weight: String?,
    val shortAddress: String?,
    val ssn:String?,
    val tin:String?
):Parcelable


fun CurrentProfile.mapToProfileInfo() = ProfileInfoModel(
    id = this.id,
    email = this.email,
    firstName = this.firstName,
    lastName = this.lastName,
    middleName = this.middleName,
    avatar = this.avatar?.fullUrl,
    dateOfBirth = this.dateOfBirth,
    sex = this.sex?.let { ProfileSex.getSexByTag(it) },
    height = this.height,
    weight = this.weight,
    shortAddress = shortAddress,
    ssn = this.ssn,
    tin = this.tin
)


