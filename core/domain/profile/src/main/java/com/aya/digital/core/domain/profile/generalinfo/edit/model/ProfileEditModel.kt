package com.aya.digital.core.domain.profile.generalinfo.edit.model

import com.aya.digital.core.model.ProfileSex
import com.aya.digital.core.network.model.request.ProfileBody
import java.time.LocalDate

data class ProfileEditModel(
    var firstName: String? = null,
    var lastName: String? = null,
    var middleName: String? = null,
    var dateOfBirth: LocalDate? = null,
    var sex: ProfileSex? = null,
    var height: String? = null,
    var weight: String? = null,
    var shortAddress: String? = null,
    var ssn:String? = null,
    var tin:String? = null,
) {

}
