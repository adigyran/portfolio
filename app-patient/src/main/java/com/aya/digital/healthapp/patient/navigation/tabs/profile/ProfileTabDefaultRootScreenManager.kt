package com.aya.digital.healthapp.patient.navigation.tabs.profile

import com.aya.digital.core.navigation.graph.DefaultRootScreenManager
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen
import com.aya.digital.feature.auth.container.navigation.AuthContainerScreen

class ProfileTabDefaultRootScreenManager : DefaultRootScreenManager {
    override fun processDefaultRootScreen(): HealthAppFragmentScreen  = AuthContainerScreen
}