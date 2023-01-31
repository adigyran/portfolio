package com.aya.digital.core.repository.di

import com.aya.digital.core.data.repositories.profile.AuthRepository
import com.aya.digital.core.repository.profile.AuthRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import org.kodein.di.instance

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
