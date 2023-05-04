package com.aya.digital.healthapp.patient.navigation.tabs.appointments

import com.aya.digital.core.feature.tabviews.appointments.navigation.AppointmentsNavigationEvents
import com.aya.digital.core.feature.tabviews.appointments.navigation.AppointmentsScreen
import com.aya.digital.core.feature.tabviews.home.navigation.HomeScreen
import com.aya.digital.core.feature.videocall.videocallscreen.navigation.VideoCallScreenNavigationEvents
import com.aya.digital.core.feature.videocall.videocallscreen.navigation.VideoCallScreenScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.navigator.FragmentContainerGraph
import com.aya.digital.feature.tabs.home.navigation.HomeTabNavigationEvents
import com.github.terrakok.cicerone.Router

class AppointmentsTabCoordinatorGraph : FragmentContainerGraph {
    override fun processEvent(
        event: CoordinatorEvent,
        navigationRouter: Router,
        parentCoordinatorRouter: CoordinatorRouter
    ) {
        when (event) {
            AppointmentsNavigationEvents.OpenDefaultScreen -> {
                navigationRouter.newRootScreen(AppointmentsScreen)
            }
            is AppointmentsNavigationEvents.OpenVideoCall ->
            {
                navigationRouter.navigateTo(VideoCallScreenScreen(event.roomId))
            }
            VideoCallScreenNavigationEvents.Back -> {
                navigationRouter.exit()
            }
            CoordinatorEvent.Back ->
            {
                navigationRouter.exit()
            }
            else -> parentCoordinatorRouter.sendEvent(event)
        }


    }


}