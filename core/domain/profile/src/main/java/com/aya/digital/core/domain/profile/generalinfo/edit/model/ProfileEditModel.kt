package com.aya.digital.core.domain.profile.generalinfo.edit.model

import com.aya.digital.core.model.ProfileSex

data class ProfileEditModel(
    var firstName: String? = null,
    var lastName: String? = null,
    var middleName: String? = null,
    var dateOfBirth: java.time.LocalDate? = null,
    var sex: ProfileSex? = null,
    var height: String? = null,
    var weight: String? = null,
    var shortAddress: String? = null
) {

}