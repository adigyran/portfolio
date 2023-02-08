package com.aya.digital.core.feature.bottomnavhost.navigation

import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.github.terrakok.cicerone.Router

class BottomNavHostCoordinator(
    private val navigationRouter: Router,
    private val parentCoordinatorRouter: CoordinatorRouter
) : Coordinator {
    override fun consumeEvent(event: CoordinatorEvent) {
        TODO("Not yet implemented")
    }
}