package com.aya.digital.core.network

import com.aya.digital.core.networkbase.moshi.adapters.BigDecimalJsonAdapter
import com.aya.digital.core.networkbase.moshi.adapters.NullPrimitiveAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import org.kodein.di.instance

fun moshiModule() = DI.Module("moshiModule") {
    bind<Moshi>(Constants.MOSHI_TAG) with eagerSingleton {
        return@eagerSingleton Moshi.Builder()
            .add(NullPrimitiveAdapter())
            .add(BigDecimalJsonAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}