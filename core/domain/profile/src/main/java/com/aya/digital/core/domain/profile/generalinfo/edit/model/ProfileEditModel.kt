package com.aya.digital.core.domain.profile.generalinfo.edit.model

import com.aya.digital.core.model.ProfileSex

data class ProfileEditModel(
    val firstName: String?,
    val lastName: String?,
    val middleName: String?,
    val dateOfBirth: java.time.LocalDate?,
    val sex: ProfileSex?,
    val height: String?,
    val weight: String?,
    val shortAddress: String?
) {

}