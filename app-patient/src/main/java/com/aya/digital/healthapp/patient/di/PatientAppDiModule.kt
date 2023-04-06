package com.aya.digital.healthapp.patient.di

import com.aya.digital.core.navigation.AppFlavour
import com.aya.digital.core.navigation.bottomnavigation.BottomNavigationItemListener
import com.aya.digital.core.navigation.bottomnavigation.BottomNavigationMenuProvider
import com.aya.digital.core.navigation.graph.DefaultBottomNavScreenManager
import com.aya.digital.core.navigation.graph.DefaultRootScreenManager
import com.aya.digital.core.navigation.graph.coordinator.BottomCoordinatorGraph
import com.aya.digital.core.navigation.graph.coordinator.RootCoordinatorGraph
import com.aya.digital.core.navigation.graph.navigator.BottomNavigationGraph
import com.aya.digital.core.navigation.graph.navigator.FragmentContainerGraph
import com.aya.digital.core.navigation.graph.navigator.RootNavigationGraph
import com.aya.digital.healthapp.patient.PatientAppFlavour
import com.aya.digital.healthapp.patient.navigation.root.PatientAppDefaultRootScreenManager
import com.aya.digital.healthapp.patient.navigation.auth.PatientAuthGraph
import com.aya.digital.healthapp.patient.navigation.bottom.*
import com.aya.digital.healthapp.patient.navigation.root.PatientRootCoordinatorGraph
import com.aya.digital.healthapp.patient.navigation.root.PatientRootNavigationGraph
import com.aya.digital.healthapp.patient.navigation.tabs.appointments.AppointmentsTabCoordinatorGraph
import com.aya.digital.healthapp.patient.navigation.tabs.appointments.AppointmentsTabDefaultRootScreenManager
import com.aya.digital.healthapp.patient.navigation.tabs.doctorsearch.DoctorSearchTabCoordinatorGraph
import com.aya.digital.healthapp.patient.navigation.tabs.doctorsearch.DoctorSearchTabDefaultRootScreenManager
import com.aya.digital.healthapp.patient.navigation.tabs.home.HomeTabCoordinatorGraph
import com.aya.digital.healthapp.patient.navigation.tabs.home.HomeTabDefaultRootScreenManager
import com.aya.digital.healthapp.patient.navigation.tabs.profile.ProfileTabCoordinatorGraph
import com.aya.digital.healthapp.patient.navigation.tabs.profile.ProfileTabDefaultRootScreenManager

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun patientAppDiModule() = DI.Module("patientAppDiModule") {

    bind<AppFlavour>() with singleton { PatientAppFlavour() }

    bind<RootNavigationGraph>("root_navigation") with singleton { PatientRootNavigationGraph() }
    bind<RootCoordinatorGraph>("root_navigation") with singleton {
        PatientRootCoordinatorGraph(
            instance()
        )
    }

    bind<FragmentContainerGraph>("auth_navigation") with singleton { PatientAuthGraph() }

    bind<DefaultRootScreenManager>("root_navigation") with singleton { PatientAppDefaultRootScreenManager() }

    bind<BottomNavigationMenuProvider>() with singleton { BottomNavigationMenuProviderImpl() }

    bind<BottomCoordinatorGraph>() with singleton { BottomNavigationCoordinatorGraphImpl(instance()) }

    bind<BottomNavigationGraph>() with singleton { BottomNavigationNavigationGraphImpl() }

    bind<DefaultBottomNavScreenManager>() with singleton { DefaultBottomNavScreenManagerImpl() }
    bind<BottomNavigationItemListener>() with singleton { BottomNavigationItemListenerImpl() }

    //tabs
    bind<DefaultRootScreenManager>("home_tab_navigation") with singleton { HomeTabDefaultRootScreenManager() }
    bind<FragmentContainerGraph>("home_tab_navigation") with singleton { HomeTabCoordinatorGraph() }
    bind<DefaultRootScreenManager>("profile_tab_navigation") with singleton { ProfileTabDefaultRootScreenManager() }
    bind<FragmentContainerGraph>("profile_tab_navigation") with singleton { ProfileTabCoordinatorGraph() }
    bind<DefaultRootScreenManager>("doctors_search_tab_navigation") with singleton { DoctorSearchTabDefaultRootScreenManager() }
    bind<FragmentContainerGraph>("doctors_search_tab_navigation") with singleton { DoctorSearchTabCoordinatorGraph() }
    bind<DefaultRootScreenManager>("appointments_tab_navigation") with singleton { AppointmentsTabDefaultRootScreenManager() }
    bind<FragmentContainerGraph>("appointments_tab_navigation") with singleton { AppointmentsTabCoordinatorGraph() }

}