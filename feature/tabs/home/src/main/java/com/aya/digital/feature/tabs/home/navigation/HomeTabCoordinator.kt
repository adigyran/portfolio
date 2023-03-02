package com.aya.digital.feature.tabs.home.navigation

import android.content.Context
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.navigator.FragmentContainerGraph
import com.github.terrakok.cicerone.Router

class HomeTabCoordinator(
    private val navigationRouter: Router,
    private val parentCoordinatorRouter: CoordinatorRouter,
    private val context: Context,
    private val coordinatorGraph : FragmentContainerGraph,
) : Coordinator {
    override fun consumeEvent(event: CoordinatorEvent) {
        coordinatorGraph.processEvent(event, navigationRouter, parentCoordinatorRouter)
    }

}
