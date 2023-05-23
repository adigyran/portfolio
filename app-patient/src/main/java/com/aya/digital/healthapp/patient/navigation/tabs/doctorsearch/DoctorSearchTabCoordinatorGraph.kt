package com.aya.digital.healthapp.patient.navigation.tabs.doctorsearch

import com.aya.digital.core.feature.doctors.doctorcard.navigation.DoctorCardScreen
import com.aya.digital.core.feature.tabviews.doctorsearch.navigation.DoctorSearchNavigationEvents
import com.aya.digital.core.feature.tabviews.doctorsearch.navigation.DoctorSearchScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.navigator.FragmentContainerGraph
import com.github.terrakok.cicerone.Router

class DoctorSearchTabCoordinatorGraph : FragmentContainerGraph {
    override fun processEvent(
        event: CoordinatorEvent,
        navigationRouter: Router,
        parentCoordinatorRouter: CoordinatorRouter
    ) {
        when (event) {
            DoctorSearchNavigationEvents.OpenDefaultScreen -> {
                navigationRouter.newRootScreen(DoctorSearchScreen)
            }
            is DoctorSearchNavigationEvents.OpenDoctorCard -> {
                navigationRouter.navigateTo(DoctorCardScreen(event.doctorId))
            }
            CoordinatorEvent.Back ->
            {
                navigationRouter.exit()
            }
            else -> parentCoordinatorRouter.sendEvent(event)
        }


    }


}