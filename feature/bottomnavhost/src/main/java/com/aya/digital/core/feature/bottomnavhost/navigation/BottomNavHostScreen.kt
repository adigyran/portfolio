package com.aya.digital.core.feature.bottomnavhost.navigation

import com.aya.digital.core.feature.bottomnavhost.ui.BottomNavHostView
import com.aya.digital.core.navigation.bottomnavigation.StartScreen
import com.aya.digital.core.navigation.screen.HealthAppBottomNavHostFragmentScreen


data class BottomNavHostScreen(private val startScreen: StartScreen) : HealthAppBottomNavHostFragmentScreen(fragmentCreator = {BottomNavHostView.getNewInstance(startScreen)})