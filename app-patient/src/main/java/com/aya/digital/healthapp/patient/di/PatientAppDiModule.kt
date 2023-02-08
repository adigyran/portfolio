package com.aya.digital.healthapp.patient.di

import com.aya.digital.core.navigation.AppFlavour
import com.aya.digital.core.navigation.graph.DefaultRootScreenManager
import com.aya.digital.core.navigation.graph.coordinator.RootCoordinatorGraph
import com.aya.digital.core.navigation.graph.navigator.FragmentContainerGraph
import com.aya.digital.core.navigation.graph.navigator.RootNavigationGraph
import com.aya.digital.healthapp.patient.PatientAppFlavour
import com.aya.digital.healthapp.patient.navigation.root.PatientAppDefaultRootScreenManager
import com.aya.digital.healthapp.patient.navigation.auth.PatientAuthGraph
import com.aya.digital.healthapp.patient.navigation.root.PatientRootCoordinatorGraph
import com.aya.digital.healthapp.patient.navigation.root.PatientRootNavigationGraph

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

    bind<DefaultRootScreenManager>() with singleton { PatientAppDefaultRootScreenManager() }


}
