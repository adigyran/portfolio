package com.aya.digital.core.feature.profile.address.navigation

import com.aya.digital.core.feature.profile.address.ui.ProfileAddressView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object ProfileAddressScreen :
    HealthAppFragmentScreen(fragmentCreator = { ProfileAddressView() })
