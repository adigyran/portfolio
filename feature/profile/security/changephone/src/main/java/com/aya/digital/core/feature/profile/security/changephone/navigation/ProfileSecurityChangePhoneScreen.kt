package com.aya.digital.core.feature.profile.security.changephone.navigation

import com.aya.digital.core.feature.profile.security.changephone.ui.ProfileSecurityChangePhoneView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

data class ProfileSecurityChangePhoneScreen(val requestCode: String, val phone:String) :
    HealthAppFragmentScreen(fragmentCreator = {
        ProfileSecurityChangePhoneView.getNewInstance(requestCode,phone)
    })
