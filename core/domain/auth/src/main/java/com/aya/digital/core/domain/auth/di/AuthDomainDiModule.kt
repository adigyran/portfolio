package com.aya.digital.core.domain.auth.di

import com.aya.digital.core.domain.auth.signin.impl.CheckIsAuthenticatedUseCaseImpl
import com.aya.digital.core.domain.auth.signin.impl.SignInUseCaseImpl
import com.aya.digital.core.domain.auth.restorepassword.*
import com.aya.digital.core.domain.auth.restorepassword.impl.RestorePasswordChangePasswordUseCaseImpl
import com.aya.digital.core.domain.auth.restorepassword.impl.RestorePasswordGetCodeUseCaseImpl
import com.aya.digital.core.domain.auth.restorepassword.impl.RestorePasswordSendCodeUseCaseImpl
import com.aya.digital.core.domain.auth.restorepassword.impl.MakeNewPasswordUseCaseImpl
import com.aya.digital.core.domain.auth.signin.*
import com.aya.digital.core.domain.auth.signin.impl.SignInOAuthUseCaseImpl
import com.aya.digital.core.domain.auth.signup.*
import com.aya.digital.core.domain.auth.signup.impl.*
import com.aya.digital.core.domain.auth.signup.impl.CheckIsVerifiedUseCaseImpl
import com.aya.digital.core.domain.auth.signup.impl.SignUpGetSelectedInsurancesUseCaseImpl
import com.aya.digital.core.domain.auth.signup.impl.SignUpUseCaseImpl
import com.aya.digital.core.domain.auth.signup.impl.VerifyRegistrationUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun authDomainDiModule() = DI.Module("authDomainDiModule") {
    bind<SignInUseCase>() with singleton { SignInUseCaseImpl(instance()) }
    bind<SignInOAuthUseCase>() with singleton { SignInOAuthUseCaseImpl(instance()) }
    bind<PerformTokenRequestOAuthUseCase>() with singleton { PerformTokenRequestOAuthUseCaseImpl(instance(),instance()) }
    bind<SignUpUseCase>() with singleton { SignUpUseCaseImpl(instance()) }
    bind<SignUpGetCodeUseCase>() with singleton { SignUpGetCodeUseCaseImpl(instance()) }

    bind<SignUpGetSelectedInsurancesUseCase>() with singleton {
        SignUpGetSelectedInsurancesUseCaseImpl(
            instance()
        )
    }
    bind<CheckIsAuthenticatedUseCase>() with singleton { CheckIsAuthenticatedUseCaseImpl(instance()) }
    bind<CheckIsVerifiedUseCase>() with singleton { CheckIsVerifiedUseCaseImpl(instance()) }
    bind<VerifyRegistrationUseCase>() with singleton { VerifyRegistrationUseCaseImpl(instance()) }

    bind<MakeNewPasswordUseCase>() with singleton { MakeNewPasswordUseCaseImpl() }
    bind<RestorePasswordSendCodeGetUserKeyUseCase>() with singleton {
        RestorePasswordSendCodeUseCaseImpl(
            instance()
        )
    }
    bind<RestorePasswordGetCodeUseCase>() with singleton {
        RestorePasswordGetCodeUseCaseImpl(
            instance()
        )
    }
    bind<RestorePasswordChangePasswordUseCase>() with singleton {
        RestorePasswordChangePasswordUseCaseImpl(
            instance()
        )
    }


}