package com.aya.digital.core.domain.appointment.di

import com.aya.digital.core.domain.appointment.base.SubscribeToPrescriptionsUseCase
import com.aya.digital.core.domain.appointment.base.impl.SubscribeToPrescriptionsUseCaseImpl
import com.aya.digital.core.navigation.AppFlavour
import com.aya.digital.core.navigation.Flavor
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun prescriptionsDomainDiModule() = DI.Module("prescriptionsDomainDiModule") {
    bind<SubscribeToPrescriptionsUseCase>() with singleton {
        SubscribeToPrescriptionsUseCaseImpl(
            instance(),
            instance()
        )
    }

}