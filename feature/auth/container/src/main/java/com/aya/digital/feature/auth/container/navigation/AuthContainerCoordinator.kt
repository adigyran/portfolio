package com.aya.digital.feature.auth.container.navigation

import android.content.Context
import android.os.UserManager
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.navigator.FragmentContainerGraph
import com.github.terrakok.cicerone.Router

class AuthContainerCoordinator(
    private val navigationRouter: Router,
    private val parentCoordinatorRouter: CoordinatorRouter,
    private val context: Context,
    private val coordinatorGraph : FragmentContainerGraph,
) : Coordinator {
    override fun consumeEvent(event: CoordinatorEvent) {
        coordinatorGraph.processEvent(event, navigationRouter, parentCoordinatorRouter)
    }

}
