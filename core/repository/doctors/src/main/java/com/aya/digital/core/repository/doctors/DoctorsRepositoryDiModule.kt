package com.aya.digital.core.repository.doctors

import com.aya.digital.core.data.doctors.repository.DoctorRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun doctorsRepositoryDiModule() = DI.Module("doctorsRepositoryDiModule") {
    bind<DoctorRepository>() with singleton { DoctorRepositoryImpl(instance(),instance()) }
}