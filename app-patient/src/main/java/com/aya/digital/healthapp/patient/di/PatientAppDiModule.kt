package com.aya.digital.healthapp.patient.di

import com.aya.digital.core.navigation.AppFlavour
import com.aya.digital.healthapp.patient.PatientAppFlavour
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.eagerSingleton

fun patientAppDiModule() = DI.Module("patientAppDiModule") {
    bind<AppFlavour>() with eagerSingleton { PatientAppFlavour() }

}