package com.aya.digital.core.network.di

import com.aya.digital.core.network.main.Constants
import com.aya.digital.core.network.main.RetrofitTags
import org.kodein.di.*
import retrofit2.Retrofit

internal fun retrofitModule() = DI.Module("retrofitModule") {
    bind<Retrofit.Builder>() with singleton {
        return@singleton Retrofit.Builder()
            .client(instance(Constants.OKHTTP_CLIENT_TAG))
            .addConverterFactory(instance(Constants.MOSHI_CONVERTER_FACTORY_TAG))
            .addCallAdapterFactory(instance(Constants.RXJAVA_CALL_ADAPTER_TAG))
    }

    bind<Retrofit>() with singleton {
        instance<Retrofit.Builder>()
            .baseUrl(Constants.BASE_URL_API)
            .build()
    }
    bind<Retrofit>(RetrofitTags.RETROFIT_AUTH_TAG) with singleton {
        instance<Retrofit.Builder>()
            .baseUrl(Constants.BASE_AUTH_URL_API)
            .build()
    }

    bind<Retrofit>(RetrofitTags.RETROFIT_TOKEN_TAG) with singleton {
        instance<Retrofit.Builder>()
            .baseUrl(Constants.BASE_TOKEN_URL_API)
            .build()
    }
}

inline fun <reified T> createApiService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}