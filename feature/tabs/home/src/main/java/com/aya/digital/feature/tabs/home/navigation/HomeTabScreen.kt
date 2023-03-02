package com.aya.digital.feature.tabs.home.navigation

import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen
import com.aya.digital.core.navigation.screen.HealthAppTabFragmentScreen
import com.aya.digital.feature.tabs.home.ui.HomeTabView

object HomeTabScreen : HealthAppTabFragmentScreen(fragmentCreator = { HomeTabView.getNewInstance()})
