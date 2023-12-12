package com.aya.digital.core.repository.appointment

import com.aya.digital.core.data.appointment.repository.PrescriptionsRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun prescriptionsRepositoryDiModule() = DI.Module("prescriptionsRepositoryDiModule") {
    bind<PrescriptionsRepository>() with singleton { PrescriptionsRepositoryImpl(instance()) }
}