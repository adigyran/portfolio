package com.aya.digital.core.network.di

import com.aya.digital.core.network.main.Constants
import com.aya.digital.core.network.main.interceptors.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import java.util.concurrent.TimeUnit

internal fun okHttpClientModule() = DI.Module("okHttpClientModule") {

    fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level =
            HttpLoggingInterceptor.Level.BODY
    }

    bind<OkHttpClient>(Constants.OKHTTP_CLIENT_TAG) with singleton {
        val builder = OkHttpClient.Builder()
        return@singleton builder
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(instance<AuthInterceptor>(Constants.AUTH_INTERCEPTOR_TAG))
            .addInterceptor(getLoggingInterceptor())
            .build()
    }
}