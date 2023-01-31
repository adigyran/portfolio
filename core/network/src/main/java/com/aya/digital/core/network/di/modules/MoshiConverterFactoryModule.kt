package com.aya.digital.core.network

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import org.kodein.di.instance
import retrofit2.converter.moshi.MoshiConverterFactory

internal fun moshiConverterFactoryModule() = DI.Module("moshiConverterFactoryModule") {
    bind<MoshiConverterFactory>(Constants.MOSHI_CONVERTER_FACTORY_TAG) with eagerSingleton {
        return@eagerSingleton MoshiConverterFactory.create(instance(Constants.MOSHI_TAG))
    }
}

