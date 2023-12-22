package com.aya.digital.core.feature.prescriptions.list.navigation

import com.aya.digital.core.feature.prescriptions.list.ui.ProfileInsuranceListView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object ProfileInsuranceListScreen :
    HealthAppFragmentScreen(fragmentCreator = { ProfileInsuranceListView() })
