package com.aya.digital.healthapp.patient.navigation.tabs.home

import com.aya.digital.core.feature.tabviews.appointments.navigation.AppointmentsScreen
import com.aya.digital.core.feature.tabviews.home.navigation.HomeScreen
import com.aya.digital.core.navigation.graph.DefaultRootScreenManager
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

class HomeTabDefaultRootScreenManager : DefaultRootScreenManager {
    override fun processDefaultRootScreen(): HealthAppFragmentScreen  = HomeScreen
}