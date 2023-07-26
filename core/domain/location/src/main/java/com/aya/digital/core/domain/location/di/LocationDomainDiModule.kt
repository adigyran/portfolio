package com.aya.digital.core.domain.location.di


import com.aya.digital.core.domain.location.GetLocationUseCase
import com.aya.digital.core.domain.location.impl.GetLocationUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun locationDomainDiModule() = DI.Module("locationDomainDiModule") {

    bind<GetLocationUseCase>() with singleton { GetLocationUseCaseImpl(instance(),instance()) }

}
