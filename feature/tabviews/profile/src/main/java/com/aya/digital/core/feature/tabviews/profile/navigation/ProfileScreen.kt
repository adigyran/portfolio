package com.aya.digital.core.feature.tabviews.profile.navigation

import com.aya.digital.core.feature.tabviews.profile.ui.ProfileView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object ProfileScreen : HealthAppFragmentScreen(fragmentCreator = { ProfileView() })
