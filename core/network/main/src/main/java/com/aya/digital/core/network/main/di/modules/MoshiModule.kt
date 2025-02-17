package com.aya.digital.core.network

import com.aya.digital.core.network.main.Constants
import com.aya.digital.core.networkbase.moshi.adapters.BigDecimalJsonAdapter
import com.aya.digital.core.networkbase.moshi.adapters.NullPrimitiveAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

internal fun moshiModule() = DI.Module("moshiModule") {
    bind<Moshi>(Constants.MOSHI_TAG) with singleton {
        return@singleton Moshi.Builder()
            .add(NullPrimitiveAdapter())
            .add(BigDecimalJsonAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}