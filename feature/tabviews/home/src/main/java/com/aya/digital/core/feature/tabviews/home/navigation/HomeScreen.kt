package com.aya.digital.core.feature.tabviews.home.navigation

import com.aya.digital.core.feature.tabviews.home.ui.HomeView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object HomeScreen : HealthAppFragmentScreen(fragmentCreator = { HomeView() })
