package com.aya.digital.core.feature.profile.prescriptions.view.navigation

import com.aya.digital.core.feature.profile.prescriptions.view.ui.ProfileInsuranceAddView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

data class ProfilePrescriptionsViewScreen(val requestCode: String, val insuranceId: Int?) :
    HealthAppFragmentScreen(fragmentCreator = {
        ProfileInsuranceAddView.getNewInstance(
            requestCode = requestCode,
            insuranceId = insuranceId
        )
    })
