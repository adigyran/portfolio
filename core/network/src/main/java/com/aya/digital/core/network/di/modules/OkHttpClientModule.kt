package com.aya.digital.core.network.di

import com.aya.digital.core.network.Constants
import okhttp3.OkHttpClient
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import java.util.concurrent.TimeUnit

internal fun okHttpClientModule() = DI.Module("okHttpClientModule") {
    bind<OkHttpClient>(Constants.OKHTTP_CLIENT_TAG) with eagerSingleton {
        val builder = OkHttpClient.Builder()
        return@eagerSingleton builder
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
}