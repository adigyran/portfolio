package com.aya.digital.core.network.interceptors.interceptors

import com.aya.digital.core.datastore.HealthAppAuthDataSource
import com.aya.digital.core.network.interceptors.InterceptorsConstants.AUTHORIZATION
import com.aya.digital.core.network.interceptors.InterceptorsConstants.BEARER
import com.aya.digital.core.network.interceptors.ext.accessTokenBlocking
import com.aya.digital.core.network.interceptors.ext.bearerTokenBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val authDataSource: HealthAppAuthDataSource) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val bearerToken = authDataSource.bearerTokenBlocking()
        val request = chain.request()
            .newBuilder()

        if(bearerToken.isNotBlank())  request.addHeader(AUTHORIZATION, bearerToken)
        return chain.proceed(request.build())
    }


}