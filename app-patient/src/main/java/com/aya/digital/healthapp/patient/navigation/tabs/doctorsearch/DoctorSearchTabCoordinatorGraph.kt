package com.aya.digital.healthapp.patient.navigation.tabs.doctorsearch

import com.aya.digital.core.feature.doctors.doctorcard.navigation.DoctorCardScreen
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.navigation.DoctorSearchMapNavigationEvents
import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.navigation.DoctorSearchMapScreen
import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.navigation.DoctorSearchListNavigationEvents
import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.navigation.DoctorSearchListScreen
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.navigation.DoctorSearchContainerNavigationEvents
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.navigation.DoctorSearchContainerScreen
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.navigator.FragmentContainerGraph
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.navigation.DoctorsClusterListDialogNavigationEvents
import com.aya.digital.feature.bottomdialogs.doctorsclusterlistdialog.viewmodel.DoctorsClusterListDialogViewModel
import com.aya.digital.feature.rootcontainer.navigation.RootContainerNavigationEvents
import com.github.terrakok.cicerone.Router

class DoctorSearchTabCoordinatorGraph : FragmentContainerGraph {
    override fun processEvent(
        event: CoordinatorEvent,
        navigationRouter: Router,
        parentCoordinatorRouter: CoordinatorRouter
    ) {
        when (event) {
            DoctorSearchContainerNavigationEvents.OpenDefaultScreen -> {
                navigationRouter.replaceScreen(DoctorSearchListScreen)
            }

            DoctorSearchContainerNavigationEvents.OpenMap -> {
                navigationRouter.replaceScreen(DoctorSearchMapScreen)
            }

            DoctorSearchContainerNavigationEvents.OpenList -> {
                navigationRouter.replaceScreen(DoctorSearchListScreen)
            }
            is DoctorSearchMapNavigationEvents.OpenDoctorCard -> {
                navigationRouter.navigateTo(DoctorCardScreen(event.doctorId))
            }

            is DoctorSearchListNavigationEvents.OpenDoctorCard -> {
                navigationRouter.navigateTo(DoctorCardScreen(event.doctorId))
            }
            CoordinatorEvent.Back ->
            {
                navigationRouter.exit()
            }
            is DoctorSearchListNavigationEvents.SelectInsuranceCompanies ->
            {
                parentCoordinatorRouter.sendEvent(RootContainerNavigationEvents.SelectMultipleItems(event.requestCode,
                    mutableSetOf<Int>().apply {
                        event.selectedInsurancesIds?.let {
                            addAll(it)
                        }
                    }))
            }
            is DoctorSearchListNavigationEvents.SelectSpecialisations ->
            {
                parentCoordinatorRouter.sendEvent(RootContainerNavigationEvents.SelectMultipleItems(event.requestCode,
                    mutableSetOf<Int>().apply {
                        event.selectedSpecialisationsIds?.let {
                            addAll(it)
                        }
                    }))
            }
            is DoctorSearchListNavigationEvents.SelectLocation ->
            {
                parentCoordinatorRouter.sendEvent(RootContainerNavigationEvents.SelectSingleItem(event.requestCode,event.selectedLocation))
            }
            else -> parentCoordinatorRouter.sendEvent(event)
        }


    }


}