package com.aya.digital.healthapp.doctor.navigation.root

import com.aya.digital.core.navigation.graph.DefaultRootScreenManager
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen
import com.aya.digital.feature.auth.container.navigation.AuthContainerScreen

class DoctorAppDefaultRootScreenManager : DefaultRootScreenManager {
    override fun processDefaultRootScreen(): HealthAppFragmentScreen  = AuthContainerScreen
}