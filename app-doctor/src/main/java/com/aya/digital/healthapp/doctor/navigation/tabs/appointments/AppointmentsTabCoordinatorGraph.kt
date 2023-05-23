package com.aya.digital.healthapp.doctor.navigation.tabs.appointments

import com.aya.digital.core.feature.appointments.appointmentcard.navigation.AppointmentCardNavigationEvents
import com.aya.digital.core.feature.appointments.appointmentcard.navigation.AppointmentCardScreen
import com.aya.digital.core.feature.tabviews.appointments.navigation.AppointmentsNavigationEvents
import com.aya.digital.core.feature.tabviews.appointments.navigation.AppointmentsScreen
import com.aya.digital.core.feature.videocall.videocallscreen.navigation.VideoCallScreenNavigationEvents
import com.aya.digital.core.feature.videocall.videocallscreen.navigation.VideoCallScreenScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.navigator.FragmentContainerGraph
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

            is AppointmentCardNavigationEvents.OpenVideoCall -> {
                navigationRouter.navigateTo(VideoCallScreenScreen(event.roomId))
            }

            is AppointmentsNavigationEvents.OpenAppointment -> {
                navigationRouter.navigateTo(AppointmentCardScreen(event.appointmentId))
            }

            VideoCallScreenNavigationEvents.Back -> {
                navigationRouter.exit()
            }

            CoordinatorEvent.Back -> {
                navigationRouter.exit()
            }

            else -> parentCoordinatorRouter.sendEvent(event)
        }


    }


}