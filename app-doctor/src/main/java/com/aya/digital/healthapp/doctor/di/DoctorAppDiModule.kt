package com.aya.digital.healthapp.doctor.di

import com.aya.digital.core.navigation.AppFlavour
import com.aya.digital.core.navigation.graph.navigator.RootNavigationGraph
import com.aya.digital.healthapp.doctor.DoctorAppFlavour
import com.aya.digital.healthapp.doctor.navigation.DoctorRootNavigationGraph
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun doctorAppDiModule() = DI.Module("doctorAppDiModule") {
    bind<AppFlavour>() with singleton { DoctorAppFlavour() }

    bind<RootNavigationGraph>("root_navigation") with singleton { DoctorRootNavigationGraph() }

}
