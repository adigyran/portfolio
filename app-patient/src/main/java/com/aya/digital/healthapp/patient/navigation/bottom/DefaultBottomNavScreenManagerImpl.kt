package com.aya.digital.healthapp.patient.navigation.bottom

import com.aya.digital.core.navigation.bottomnavigation.StartScreen
import com.aya.digital.core.navigation.graph.DefaultBottomNavScreenManager
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen
import com.aya.digital.feature.tabs.appointments.navigation.AppointmentsTabScreen
import com.aya.digital.feature.tabs.doctorsearch.navigation.DoctorSearchTabScreen
import com.aya.digital.feature.tabs.home.navigation.HomeTabScreen
import com.aya.digital.feature.tabs.profile.navigation.ProfileTabScreen

class DefaultBottomNavScreenManagerImpl : DefaultBottomNavScreenManager {
    override fun processDefaultBottomNavScreen(startScreen: StartScreen): HealthAppFragmentScreen =
        when(startScreen) {
            StartScreen.HOME -> HomeTabScreen
            StartScreen.DOCTOR_SEARCH -> DoctorSearchTabScreen
            StartScreen.APPOINTMENTS -> AppointmentsTabScreen
            StartScreen.PROFILE -> ProfileTabScreen
        }
}