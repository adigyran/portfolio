package com.aya.digital.core.domain.profile.di
import com.aya.digital.core.domain.profile.generalinfo.GetProfileBriefUseCase
import com.aya.digital.core.domain.profile.generalinfo.GetProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.GetProfileInfoUseCaseImpl
import com.aya.digital.core.domain.profile.generalinfo.GetProfileUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun profileDomainDiModule() = DI.Module("profileDomainDiModule") {
    bind<GetProfileBriefUseCase>() with singleton { GetProfileUseCaseImpl(instance()) }
    bind<GetProfileInfoUseCase>() with singleton { GetProfileInfoUseCaseImpl(instance()) }

}