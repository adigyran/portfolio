package com.aya.digital.core.feature.profile.generalinfo.view.navigation

import com.aya.digital.core.feature.profile.generalinfo.view.ui.ProfileGeneralInfoViewView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

data class ProfileGeneralInfoViewScreen(val requestCode: String) :
    HealthAppFragmentScreen(fragmentCreator = { ProfileGeneralInfoViewView.getNewInstance(requestCode = requestCode) })
