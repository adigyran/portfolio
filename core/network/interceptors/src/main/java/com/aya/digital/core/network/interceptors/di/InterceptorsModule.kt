package com.aya.digital.core.network

import com.aya.digital.core.network.interceptors.InterceptorsConstants
import com.aya.digital.core.network.interceptors.interceptors.AuthInterceptor
import com.aya.digital.core.network.interceptors.interceptors.RefreshTokenInterceptor
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun interceptorsModule() = DI.Module("interceptorsModule") {
    bind<AuthInterceptor>(InterceptorsConstants.AUTH_INTERCEPTOR_TAG) with singleton { AuthInterceptor(instance()) }
    bind<RefreshTokenInterceptor>(InterceptorsConstants.REFRESH_TOKEN_INTERCEPTOR_TAG) with singleton { RefreshTokenInterceptor(instance(),instance()) }

}
