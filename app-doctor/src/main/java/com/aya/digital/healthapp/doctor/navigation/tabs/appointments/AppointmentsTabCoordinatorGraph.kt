package com.aya.digital.healthapp.doctor.navigation.tabs.appointments

import com.aya.digital.core.feature.appointments.appointmentcard.navigation.AppointmentCardNavigationEvents
import com.aya.digital.core.feature.appointments.appointmentcard.navigation.AppointmentCardScreen
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.navigation.AppointmentsSchedulerNavigationEvents
import com.aya.digital.core.feature.tabviews.appointmentsscheduler.navigation.AppointmentsSchedulerScreen

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
            AppointmentsSchedulerNavigationEvents.OpenDefaultScreen -> {
                navigationRouter.newRootScreen(AppointmentsSchedulerScreen)
            }

            is AppointmentCardNavigationEvents.OpenVideoCall -> {
                navigationRouter.navigateTo(VideoCallScreenScreen(event.roomId))
            }

            is AppointmentsSchedulerNavigationEvents.OpenAppointment -> {
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