package com.aya.digital.healthapp.patient.navigation.tabs.doctorsearch

import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.navigation.DoctorSearchContainerScreen
import com.aya.digital.core.navigation.graph.DefaultRootScreenManager
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

class DoctorSearchTabDefaultRootScreenManager : DefaultRootScreenManager {
    override fun processDefaultRootScreen(): HealthAppFragmentScreen  = DoctorSearchContainerScreen
}