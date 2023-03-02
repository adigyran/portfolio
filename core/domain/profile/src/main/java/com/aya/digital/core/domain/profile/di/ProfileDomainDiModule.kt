package com.aya.digital.core.domain.profile.di
import com.aya.digital.core.domain.profile.GetProfileBriefUseCase
import com.aya.digital.core.domain.profile.GetProfileUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun profileDomainDiModule() = DI.Module("profileDomainDiModule") {
    bind<GetProfileBriefUseCase>() with singleton { GetProfileUseCaseImpl(instance()) }
}