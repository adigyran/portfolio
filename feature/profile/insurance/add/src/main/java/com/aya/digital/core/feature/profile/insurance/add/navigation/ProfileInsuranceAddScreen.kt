package com.aya.digital.core.feature.profile.insurance.add.navigation

import com.aya.digital.core.feature.profile.insurance.add.ui.ProfileInsuranceAddView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

data class ProfileInsuranceAddScreen(val requestCode: String, val insuranceId: Int?) :
    HealthAppFragmentScreen(fragmentCreator = {
        ProfileInsuranceAddView.getNewInstance(
            requestCode = requestCode,
            insuranceId = insuranceId
        )
    })
