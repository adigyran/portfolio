package com.aya.digital.core.repository.profile

import com.aya.digital.core.data.profile.repository.ProfileRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun profileRepositoryDiModule() = DI.Module("profileRepositoryDiModule") {
    bind<ProfileRepository>() with singleton { ProfileRepositoryImpl(instance(),instance()) }
}