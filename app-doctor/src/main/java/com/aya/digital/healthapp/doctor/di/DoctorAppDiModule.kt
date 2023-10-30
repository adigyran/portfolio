package com.aya.digital.healthapp.doctor.di

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
import com.aya.digital.healthapp.doctor.BuildConfig
import com.aya.digital.healthapp.doctor.DoctorAppFlavour
import com.aya.digital.healthapp.doctor.navigation.root.DoctorRootCoordinatorGraph
import com.aya.digital.healthapp.doctor.navigation.root.DoctorRootNavigationGraph
import com.aya.digital.healthapp.doctor.navigation.bottom.BottomNavigationCoordinatorGraphImpl
import com.aya.digital.healthapp.doctor.navigation.bottom.BottomNavigationItemListenerImpl
import com.aya.digital.healthapp.doctor.navigation.bottom.BottomNavigationMenuProviderImpl
import com.aya.digital.healthapp.doctor.navigation.bottom.BottomNavigationNavigationGraphImpl
import com.aya.digital.healthapp.doctor.navigation.bottom.DefaultBottomNavScreenManagerImpl
import com.aya.digital.healthapp.doctor.navigation.root.DoctorAppDefaultRootScreenManager
import com.aya.digital.healthapp.doctor.navigation.tabs.appointments.AppointmentsTabCoordinatorGraph
import com.aya.digital.healthapp.doctor.navigation.tabs.appointments.AppointmentsTabDefaultRootScreenManager
import com.aya.digital.healthapp.doctor.navigation.tabs.profile.ProfileTabCoordinatorGraph
import com.aya.digital.healthapp.doctor.navigation.tabs.profile.ProfileTabDefaultRootScreenManager
import com.aya.digital.healthapp.patient.navigation.auth.DoctorAuthGraph
import com.aya.digital.healthapp.doctor.navigation.videocall.VideoCallContainerCoordinatorGraph
import com.aya.digital.healthapp.doctor.navigation.videocall.VideoCallContainerNavigationGraph
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun doctorAppDiModule() = DI.Module("doctorAppDiModule") {
    bind<AppFlavour>() with singleton { DoctorAppFlavour() }

    bind<RootNavigationGraph>("root_navigation") with singleton { DoctorRootNavigationGraph() }

    bind<RootCoordinatorGraph>("root_navigation") with singleton {
        DoctorRootCoordinatorGraph(
            instance()
        )
    }

    bind<FragmentContainerGraph>("auth_navigation") with singleton { DoctorAuthGraph() }

    bind<DefaultRootScreenManager>("root_navigation") with singleton { DoctorAppDefaultRootScreenManager() }

    bind<BottomNavigationMenuProvider>() with singleton { BottomNavigationMenuProviderImpl() }

    bind<BottomCoordinatorGraph>() with singleton { BottomNavigationCoordinatorGraphImpl(instance()) }

    bind<BottomNavigationGraph>() with singleton { BottomNavigationNavigationGraphImpl() }

    bind<DefaultBottomNavScreenManager>() with singleton { DefaultBottomNavScreenManagerImpl() }
    bind<BottomNavigationItemListener>() with singleton { BottomNavigationItemListenerImpl() }

    //tabs
    bind<DefaultRootScreenManager>("profile_tab_navigation") with singleton { ProfileTabDefaultRootScreenManager() }
    bind<FragmentContainerGraph>("profile_tab_navigation") with singleton { ProfileTabCoordinatorGraph() }
    bind<DefaultRootScreenManager>("appointments_tab_navigation") with singleton { AppointmentsTabDefaultRootScreenManager() }
    bind<FragmentContainerGraph>("appointments_tab_navigation") with singleton { AppointmentsTabCoordinatorGraph() }

    bind<RootCoordinatorGraph>("videocall_navigation") with singleton {
        VideoCallContainerCoordinatorGraph(
            instance()
        )
    }

    bind<RootNavigationGraph>("videocall_navigation") with singleton {
        VideoCallContainerNavigationGraph()
    }

    bind<String>("appGoogleKey") with singleton {
        BuildConfig.MAPS_API_KEY
    }
}
