package com.aya.digital.core.repository.address

import com.aya.digital.core.data.profile.repository.AddressRepository
import com.aya.digital.core.repository.address.impl.AddressRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun addressRepositoryDiModule() = DI.Module("addressRepositoryDiModule") {
    bind<AddressRepository>() with singleton { AddressRepositoryImpl(instance(),instance(),instance()) }
}