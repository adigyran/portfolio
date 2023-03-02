package com.aya.digital.core.repository.profile

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun profileRepositoryDiModule() = DI.Module("profileRepositoryDiModule") {
    bind<ProfileRepository>() with singleton { ProfileRepository(instance()) }
}