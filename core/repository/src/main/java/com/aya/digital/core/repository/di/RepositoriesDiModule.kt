package com.aya.digital.core.repository.di

import com.aya.digital.core.data.repositories.profile.AuthRepository
import com.aya.digital.core.repository.profile.AuthRepositoryImpl
import net.openid.appauth.AuthorizationService
import org.kodein.di.*

fun repositoriesDiModule() = DI.Module("repositoriesDiModule") {
    bind<AuthRepository>() with eagerSingleton {
        AuthRepositoryImpl(
            instance(),
            instance(),
            instance(),
            instance()
        )
    }
}
