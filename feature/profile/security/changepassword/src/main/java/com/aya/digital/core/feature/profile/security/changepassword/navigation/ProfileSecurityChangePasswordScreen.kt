package com.aya.digital.core.feature.profile.security.changepassword.navigation

import com.aya.digital.core.feature.profile.security.changepassword.ui.ProfileSecurityChangePasswordView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

data class ProfileSecurityChangePasswordScreen(val requestCode: String) :
    HealthAppFragmentScreen(fragmentCreator = {
        ProfileSecurityChangePasswordView.getNewInstance(requestCode)
    })
