package com.aya.digital.core.repository.appointment

import com.aya.digital.core.data.appointment.repository.AppointmentRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun appointmentRepositoryDiModule() = DI.Module("appointmentRepositoryDiModule") {
    bind<AppointmentRepository>() with singleton { AppointmentRepositoryImpl(instance(),instance()) }
}