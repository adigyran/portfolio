package com.aya.digital.core.repository.auth

import com.aya.digital.core.data.profile.repository.AuthRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun authRepositoryDiModule() = DI.Module("authRepositoryDiModule") {
    bind<AuthRepository>() with singleton { AuthRepositoryImpl(instance(), instance(),instance(),instance()) }
}