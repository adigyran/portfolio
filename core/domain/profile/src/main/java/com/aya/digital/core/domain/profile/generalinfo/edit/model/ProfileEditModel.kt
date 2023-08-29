package com.aya.digital.core.domain.profile.generalinfo.edit.model

import com.aya.digital.core.model.ProfileSex
import java.time.LocalDate

data class ProfileEditModel(
    var firstName: String? = null,
    var lastName: String? = null,
    var middleName: String? = null,
    var dateOfBirth: LocalDate? = null,
    var flavoredProfileEditModel: FlavoredProfileEditModel? = null
) {

}
