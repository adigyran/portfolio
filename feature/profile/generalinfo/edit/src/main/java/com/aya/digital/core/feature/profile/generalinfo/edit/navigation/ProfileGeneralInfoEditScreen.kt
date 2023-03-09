package com.aya.digital.core.feature.profile.generalinfo.edit.navigation

import com.aya.digital.core.domain.profile.generalinfo.view.model.ProfileInfoModel
import com.aya.digital.core.feature.profile.generalinfo.edit.ui.ProfileGeneralInfoEditView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

data class ProfileGeneralInfoEditScreen(
    val requestCode: String
    ) : HealthAppFragmentScreen(fragmentCreator = {
    ProfileGeneralInfoEditView.getNewInstance(
        requestCode = requestCode,
        profileModel = null
    )
})
