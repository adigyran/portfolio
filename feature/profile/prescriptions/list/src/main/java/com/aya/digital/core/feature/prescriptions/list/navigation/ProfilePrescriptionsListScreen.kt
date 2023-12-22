package com.aya.digital.core.feature.prescriptions.list.navigation

import com.aya.digital.core.feature.prescriptions.list.ui.ProfilePrescriptionsListView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object ProfilePrescriptionsListScreen :
    HealthAppFragmentScreen(fragmentCreator = { ProfilePrescriptionsListView() })
