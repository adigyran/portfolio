package com.aya.digital.core.network

import com.aya.digital.core.network.main.Constants
import org.kodein.di.*
import retrofit2.converter.moshi.MoshiConverterFactory

internal fun moshiConverterFactoryModule() = DI.Module("moshiConverterFactoryModule") {
    bind<MoshiConverterFactory>(Constants.MOSHI_CONVERTER_FACTORY_TAG) with singleton {
        return@singleton MoshiConverterFactory.create(instance(Constants.MOSHI_TAG))
    }
}

