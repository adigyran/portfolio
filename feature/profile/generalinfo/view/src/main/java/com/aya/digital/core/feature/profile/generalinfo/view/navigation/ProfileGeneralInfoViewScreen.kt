package com.aya.digital.core.feature.profile.generalinfo.view.navigation

import com.aya.digital.core.feature.profile.generalinfo.view.ui.ProfileGeneralInfoViewView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object ProfileGeneralInfoViewScreen :
    HealthAppFragmentScreen(fragmentCreator = { ProfileGeneralInfoViewView() })
