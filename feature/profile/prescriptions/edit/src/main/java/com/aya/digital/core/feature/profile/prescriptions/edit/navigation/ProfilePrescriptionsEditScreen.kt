package com.aya.digital.core.feature.profile.prescriptions.edit.navigation

import com.aya.digital.core.feature.profile.prescriptions.edit.ui.ProfileInsuranceAddView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

data class ProfilePrescriptionsEditScreen(val requestCode: String, val insuranceId: Int?) :
    HealthAppFragmentScreen(fragmentCreator = {
        ProfileInsuranceAddView.getNewInstance(
            requestCode = requestCode,
            insuranceId = insuranceId
        )
    })
