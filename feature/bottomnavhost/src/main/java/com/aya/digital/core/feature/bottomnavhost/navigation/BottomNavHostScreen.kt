package com.aya.digital.core.feature.bottomnavhost.navigation

import com.aya.digital.core.feature.bottomnavhost.ui.BottomNavHostView
import com.aya.digital.core.navigation.screen.HealthAppBottomNavHostFragmentScreen


object BottomNavHostScreen : HealthAppBottomNavHostFragmentScreen(fragmentCreator = {BottomNavHostView()})