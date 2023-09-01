package com.aya.digital.core.domain.home.di

import com.aya.digital.core.domain.home.lastupdates.GetLastUpdatesUseCase
import com.aya.digital.core.domain.home.lastupdates.impl.GetLastUpdatesUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun homeDomainDiModule() = DI.Module("homeDomainDiModule") {
    bind<GetLastUpdatesUseCase>() with singleton { GetLastUpdatesUseCaseImpl(instance(),instance()) }

}