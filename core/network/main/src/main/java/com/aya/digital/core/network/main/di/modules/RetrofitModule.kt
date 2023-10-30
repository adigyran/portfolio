package com.aya.digital.core.network.main.di.modules

import com.aya.digital.core.network.main.Constants
import com.aya.digital.core.network.main.RetrofitTags
import org.kodein.di.*
import retrofit2.Retrofit

internal fun retrofitModule() = DI.Module("retrofitModule") {
    bind<Retrofit.Builder>(Constants.RETROFIT_BUILDER_NORMAL) with singleton {
        return@singleton Retrofit.Builder()
            .client(instance(Constants.OKHTTP_CLIENT_TAG))
            .addConverterFactory(instance(Constants.MOSHI_CONVERTER_FACTORY_TAG))
            .addCallAdapterFactory(instance(Constants.RXJAVA_CALL_ADAPTER_TAG))
    }

    bind<Retrofit.Builder>(Constants.RETROFIT_BUILDER_TOKEN) with singleton {
        return@singleton Retrofit.Builder()
            .client(instance(Constants.OKHTTP_CLIENT_TOKEN_TAG))
            .addConverterFactory(instance(Constants.MOSHI_CONVERTER_FACTORY_TAG))
            .addCallAdapterFactory(instance(Constants.RXJAVA_CALL_ADAPTER_TAG))
    }

    bind<Retrofit.Builder>(Constants.RETROFIT_BUILDER_EXTERNAL) with singleton {
        return@singleton Retrofit.Builder()
            .client(instance(Constants.OKHTTP_CLIENT_EXTERNAL_TAG))
            .addConverterFactory(instance(Constants.MOSHI_CONVERTER_FACTORY_TAG))
            .addCallAdapterFactory(instance(Constants.RXJAVA_CALL_ADAPTER_TAG))
    }

    bind<Retrofit>() with singleton {
        instance<Retrofit.Builder>(Constants.RETROFIT_BUILDER_NORMAL)
            .baseUrl(Constants.BASE_URL_API)
            .build()
    }
    bind<Retrofit>(RetrofitTags.RETROFIT_AUTH_TAG) with singleton {
        instance<Retrofit.Builder>(Constants.RETROFIT_BUILDER_NORMAL)
            .baseUrl(Constants.BASE_AUTH_URL_API)
            .build()
    }

    bind<Retrofit>(RetrofitTags.RETROFIT_TOKEN_TAG) with singleton {
        instance<Retrofit.Builder>(Constants.RETROFIT_BUILDER_TOKEN)
            .baseUrl(Constants.BASE_AUTH_URL_API)
            .build()
    }

    bind<Retrofit>(RetrofitTags.RETROFIT_TELEHEALTH_TOKEN_TAG) with singleton {
        instance<Retrofit.Builder>(Constants.RETROFIT_BUILDER_NORMAL)
            .baseUrl(Constants.BASE_TELEHEALTH_URL_API)
            .build()
    }

    bind<Retrofit>(RetrofitTags.RETROFIT_GEOCODING_API_TAG) with singleton {
        instance<Retrofit.Builder>(Constants.RETROFIT_BUILDER_EXTERNAL)
            .baseUrl(Constants.BASE_GEOCODING_URL_API)
            .build()
    }
}

inline fun <reified T> createApiService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}