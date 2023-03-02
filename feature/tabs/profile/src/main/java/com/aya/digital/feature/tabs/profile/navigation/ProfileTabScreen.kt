package com.aya.digital.feature.tabs.profile.navigation

import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen
import com.aya.digital.core.navigation.screen.HealthAppTabFragmentScreen
import com.aya.digital.feature.tabs.profile.ui.ProfileTabView

object ProfileTabScreen : HealthAppTabFragmentScreen(fragmentCreator = { ProfileTabView.getNewInstance()})
