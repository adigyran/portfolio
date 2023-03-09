package com.aya.digital.core.feature.profile.security.changeemailphone.navigation

import com.aya.digital.core.feature.profile.security.changeemailphone.ui.ProfileSecurityChangeEmailPhoneView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

data class ProfileSecurityChangeEmailPhoneScreen(val requestCode: String) :
    HealthAppFragmentScreen(fragmentCreator = {
        ProfileSecurityChangeEmailPhoneView.getNewInstance(requestCode)
    })
