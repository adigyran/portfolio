package com.aya.digital.core.domain.auth.di

import com.aya.digital.core.domain.auth.*
import com.aya.digital.core.domain.auth.impl.*
import com.aya.digital.core.domain.auth.impl.CheckIsAuthenticatedUseCaseImpl
import com.aya.digital.core.domain.auth.impl.SignInUseCaseImpl
import com.aya.digital.core.domain.auth.impl.SignUpGetSelectedInsurancesUseCaseImpl
import com.aya.digital.core.domain.auth.impl.SignUpUseCaseImpl
import com.aya.digital.core.domain.auth.impl.VerifyRegistrationUseCaseImpl
import com.aya.digital.core.domain.auth.RestorePasswordChangePasswordUseCase
import com.aya.digital.core.domain.auth.RestorePasswordGetCodeUseCase
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun authDomainDiModule() = DI.Module("authDomainDiModule") {
    bind<SignInUseCase>() with singleton { SignInUseCaseImpl(instance()) }
    bind<SignUpUseCase>() with singleton { SignUpUseCaseImpl(instance()) }
    bind<SignUpGetSelectedInsurancesUseCase>() with singleton {
        SignUpGetSelectedInsurancesUseCaseImpl(
            instance()
        )
    }
    bind<CheckIsAuthenticatedUseCase>() with singleton { CheckIsAuthenticatedUseCaseImpl(instance()) }
    bind<VerifyRegistrationUseCase>() with singleton { VerifyRegistrationUseCaseImpl(instance()) }

    bind<MakeNewPasswordUseCase>() with singleton { MakeNewPasswordUseCaseImpl() }
    bind<RestorePasswordSendCodeUseCase>() with singleton { RestorePasswordSendCodeUseCaseImpl(instance()) }
    bind<RestorePasswordGetCodeUseCase>() with singleton { RestorePasswordGetCodeUseCaseImpl(instance()) }
    bind<RestorePasswordChangePasswordUseCase>() with singleton { RestorePasswordChangePasswordUseCaseImpl(instance()) }


}