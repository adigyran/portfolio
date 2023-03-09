package com.aya.digital.core.feature.insurance.list.navigation

import com.aya.digital.core.feature.insurance.list.ui.ProfileInsuranceListView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object ProfileInsuranceListScreen :
    HealthAppFragmentScreen(fragmentCreator = { ProfileInsuranceListView() })
