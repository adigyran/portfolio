package com.aya.digital.core.network.di

import com.aya.digital.core.network.main.Constants
import org.kodein.di.*
import retrofit2.Retrofit

internal fun retrofitModule() = DI.Module("retrofitModule") {
    bind<Retrofit>() with singleton {
        //TODO implement proper check
        val url = when ("test") {
            "mock" -> Constants.MOCK_URL
            else -> Constants.BASE_URL_API
        }
        return@singleton Retrofit.Builder()
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