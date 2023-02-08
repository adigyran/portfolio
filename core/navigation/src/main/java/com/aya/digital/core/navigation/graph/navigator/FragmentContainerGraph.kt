package com.aya.digital.core.navigation.graph.navigator

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.github.terrakok.cicerone.Router

interface FragmentContainerGraph {
    fun processEvent(
        event: CoordinatorEvent,
        navigationRouter: Router,
        parentCoordinatorRouter: CoordinatorRouter
    )
}