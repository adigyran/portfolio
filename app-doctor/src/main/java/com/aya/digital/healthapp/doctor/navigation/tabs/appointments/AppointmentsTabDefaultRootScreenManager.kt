package com.aya.digital.healthapp.doctor.navigation.tabs.appointments

import com.aya.digital.core.feature.tabviews.appointmentsscheduler.navigation.AppointmentsSchedulerScreen
import com.aya.digital.core.navigation.graph.DefaultRootScreenManager
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

class AppointmentsTabDefaultRootScreenManager : DefaultRootScreenManager {
    override fun processDefaultRootScreen(): HealthAppFragmentScreen  = AppointmentsSchedulerScreen
}