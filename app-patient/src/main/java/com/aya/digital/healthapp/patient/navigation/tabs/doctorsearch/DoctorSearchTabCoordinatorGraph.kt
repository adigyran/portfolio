package com.aya.digital.healthapp.patient.navigation.tabs.doctorsearch

import com.aya.digital.core.feature.doctors.doctorcard.navigation.DoctorCardScreen
import com.aya.digital.core.feature.profile.insurance.add.navigation.ProfileInsuranceAddNavigationEvents
import com.aya.digital.core.feature.tabviews.doctorsearch.navigation.DoctorSearchNavigationEvents
import com.aya.digital.core.feature.tabviews.doctorsearch.navigation.DoctorSearchScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.navigator.FragmentContainerGraph
import com.aya.digital.feature.rootcontainer.navigation.RootContainerNavigationEvents
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
            is DoctorSearchNavigationEvents.SelectInsuranceCompanies ->
            {
                parentCoordinatorRouter.sendEvent(RootContainerNavigationEvents.SelectMultipleItems(event.requestCode,
                    mutableSetOf<Int>().apply {
                        event.selectedInsurancesIds?.let {
                            addAll(it)
                        }
                    }))
            }
            is DoctorSearchNavigationEvents.SelectSpecialisations ->
            {
                parentCoordinatorRouter.sendEvent(RootContainerNavigationEvents.SelectMultipleItems(event.requestCode,
                    mutableSetOf<Int>().apply {
                        event.selectedSpecialisationsIds?.let {
                            addAll(it)
                        }
                    }))
            }
            is DoctorSearchNavigationEvents.SelectLocation ->
            {
                parentCoordinatorRouter.sendEvent(RootContainerNavigationEvents.SelectSingleItem(event.requestCode,event.selectedLocation))
            }
            else -> parentCoordinatorRouter.sendEvent(event)
        }


    }


}