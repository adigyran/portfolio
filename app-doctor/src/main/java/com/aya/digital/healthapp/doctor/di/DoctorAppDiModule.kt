package com.aya.digital.healthapp.doctor.di

import com.aya.digital.core.navigation.AppFlavour
import com.aya.digital.healthapp.doctor.DoctorAppFlavour
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.eagerSingleton

fun doctorAppDiModule() = DI.Module("doctorAppDiModule") {
    bind<AppFlavour>() with eagerSingleton { DoctorAppFlavour() }
}
