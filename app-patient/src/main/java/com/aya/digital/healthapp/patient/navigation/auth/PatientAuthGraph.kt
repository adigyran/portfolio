package com.aya.digital.healthapp.patient.navigation.auth

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.navigator.FragmentContainerGraph
import com.aya.digital.feature.auth.chooser.navigation.AuthChooserScreen
import com.aya.digital.feature.auth.container.navigation.AuthContainerNavigationEvents
import com.github.terrakok.cicerone.Router

class PatientAuthGraph : FragmentContainerGraph {
    override fun processEvent(
        event: CoordinatorEvent,
        navigationRouter: Router,
        parentCoordinatorRouter: CoordinatorRouter
    ) {
       when(event)
       {
           AuthContainerNavigationEvents.OpenDefaultScreen ->
           {
               navigationRouter.navigateTo(AuthChooserScreen)
           }
       }
    }


}