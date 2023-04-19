package com.aya.digital.healthapp.patient.navigation.tabs.doctorsearch

import com.aya.digital.core.feature.doctors.doctorcard.navigation.DoctorCardNavigationEvents
import com.aya.digital.core.feature.doctors.doctorcard.navigation.DoctorCardScreen
import com.aya.digital.core.feature.tabviews.appointments.navigation.AppointmentsScreen
import com.aya.digital.core.feature.tabviews.doctorsearch.navigation.DoctorSearchNavigationEvents
import com.aya.digital.core.feature.tabviews.doctorsearch.navigation.DoctorSearchScreen
import com.aya.digital.core.feature.tabviews.home.navigation.HomeScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.navigator.FragmentContainerGraph
import com.aya.digital.feature.bottomdialogs.createappointmentdialog.navigation.CreateAppointmentDialogScreen
import com.aya.digital.feature.tabs.doctorsearch.navigation.DoctorSearchTabNavigationEvents
import com.aya.digital.feature.tabs.home.navigation.HomeTabNavigationEvents
import com.github.terrakok.cicerone.Router

class DoctorSearchTabCoordinatorGraph : FragmentContainerGraph {
    override fun processEvent(
        event: CoordinatorEvent,
        navigationRouter: Router,
        parentCoordinatorRouter: CoordinatorRouter
    ) {
        when (event) {
            HomeTabNavigationEvents.OpenDefaultScreen -> {
                navigationRouter.newRootScreen(DoctorSearchScreen)
            }
            is DoctorSearchNavigationEvents.OpenDoctorCard -> {
                navigationRouter.navigateTo(DoctorCardScreen(event.doctorId))
            }
            else -> parentCoordinatorRouter.sendEvent(event)
        }


    }


}