package com.aya.digital.core.feature.profile.security.changeemail.navigation

import com.aya.digital.core.feature.profile.security.changeemail.ui.ProfileSecurityChangeEmailView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

data class ProfileSecurityChangeEmailScreen(val requestCode: String) :
    HealthAppFragmentScreen(fragmentCreator = {
        ProfileSecurityChangeEmailView.getNewInstance(requestCode)
    })
