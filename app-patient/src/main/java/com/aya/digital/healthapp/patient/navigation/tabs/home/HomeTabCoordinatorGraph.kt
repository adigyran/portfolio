package com.aya.digital.healthapp.patient.navigation.tabs.home

import com.aya.digital.core.feature.tabviews.home.navigation.HomeScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.navigator.FragmentContainerGraph
import com.aya.digital.feature.tabs.home.navigation.HomeTabNavigationEvents
import com.github.terrakok.cicerone.Router

class HomeTabCoordinatorGraph : FragmentContainerGraph {
    override fun processEvent(
        event: CoordinatorEvent,
        navigationRouter: Router,
        parentCoordinatorRouter: CoordinatorRouter
    ) {
        when (event) {
            HomeTabNavigationEvents.OpenDefaultScreen -> {
                navigationRouter.newRootScreen(HomeScreen)
            }
            else -> parentCoordinatorRouter.sendEvent(event)
        }


    }


}