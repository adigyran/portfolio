package com.aya.digital.core.navigation.graph

import com.aya.digital.core.navigation.bottomnavigation.StartScreen
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

interface DefaultBottomNavScreenManager {

    fun processDefaultBottomNavScreen(startScreen:StartScreen):HealthAppFragmentScreen
}