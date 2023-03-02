package com.aya.digital.core.domain.auth.di

import com.aya.digital.core.domain.auth.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun authDomainDiModule() = DI.Module("authDomainDiModule") {
    bind<SignInUseCase>() with singleton { SignInUseCaseImpl(instance()) }
    bind<SignUpUseCase>() with singleton { SignUpUseCaseImpl(instance()) }
    bind<SignUpGetSelectedInsurancesUseCase>() with singleton { SignUpGetSelectedInsurancesUseCaseImpl(instance()) }
    bind<CheckIsAuthenticatedUseCase>() with singleton { CheckIsAuthenticatedUseCaseImpl(instance()) }
    bind<VerifyRegistrationUseCase>() with singleton { VerifyRegistrationUseCaseImpl(instance()) }



}