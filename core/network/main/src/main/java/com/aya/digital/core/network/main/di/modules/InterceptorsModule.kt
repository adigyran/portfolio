package com.aya.digital.core.network

import com.aya.digital.core.network.main.Constants
import com.aya.digital.core.network.main.interceptors.AuthInterceptor
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

internal fun interceptorsModule() = DI.Module("interceptorsModule") {
    bind<AuthInterceptor>(Constants.AUTH_INTERCEPTOR_TAG) with singleton { AuthInterceptor(instance()) }
}
