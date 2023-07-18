package com.aya.digital.core.repository.location

import com.aya.digital.core.data.location.repository.LocationRepository
import com.aya.digital.core.data.location.repository.PermissionsRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun locationRepositoryDiModule() = DI.Module("locationRepositoryDiModule") {
    bind<LocationRepository>() with singleton { LocationRepositoryImpl(instance(),instance()) }
    bind<PermissionsRepository>() with singleton { PermissionsRepositoryImpl() }

}