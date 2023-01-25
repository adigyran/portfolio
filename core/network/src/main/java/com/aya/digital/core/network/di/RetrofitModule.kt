package com.aya.digital.core.network.di

import com.aya.digital.core.network.Constants
import com.aya.digital.core.network.Constants.RETROFIT_TAG
import org.kodein.di.DI
import org.kodein.di.android.x.BuildConfig
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import org.kodein.di.instance
import retrofit2.Retrofit

fun retrofitModule() = DI.Module("retrofitModule") {
    bind<Retrofit>() with eagerSingleton {
        val url = when (BuildConfig.BUILD_TYPE) {
            "mock" -> Constants.MOCK_URL
            else -> Constants.BASE_URL_API
        }
        return@eagerSingleton Retrofit.Builder()
            .baseUrl(url)
            .client(instance(Constants.OKHTTP_CLIENT_TAG))
            .addConverterFactory(instance(Constants.MOSHI_CONVERTER_FACTORY_TAG))
            .addCallAdapterFactory(instance(Constants.RXJAVA_CALL_ADAPTER_TAG))
            .build()
    }
}

inline fun <reified T> createApiService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}