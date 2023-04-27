package com.aya.digital.healthapp.doctor.navigation.tabs.appointments

import com.aya.digital.core.feature.tabviews.appointments.navigation.AppointmentsScreen
import com.aya.digital.core.navigation.graph.DefaultRootScreenManager
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

class AppointmentsTabDefaultRootScreenManager : DefaultRootScreenManager {
    override fun processDefaultRootScreen(): HealthAppFragmentScreen  = AppointmentsScreen
}