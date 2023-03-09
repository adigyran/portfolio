package com.aya.digital.core.feature.profile.emergencycontact.navigation

import com.aya.digital.core.feature.profile.emergencycontact.ui.ProfileEmergencyContactView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object ProfileEmergencyContactScreen :
    HealthAppFragmentScreen(fragmentCreator = { ProfileEmergencyContactView() })
