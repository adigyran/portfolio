package com.aya.digital.core.network.main.interceptors

import android.util.Log
import com.aya.digital.core.datastore.HealthAppAuthDataSource
import com.aya.digital.core.network.main.Constants.AUTHORIZATION
import com.aya.digital.core.network.main.Constants.BEARER
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val authDataSource: HealthAppAuthDataSource) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val bearerToken = getAuthToken()
        val request = chain.request()
            .newBuilder()

        if(bearerToken.isNotBlank())  request.addHeader(AUTHORIZATION, bearerToken)
        return chain.proceed(request.build())
    }

    private fun getAuthToken():String
    {
        val accessToken = authDataSource.authUserData.doOnError { it.printStackTrace() }.blockingFirst().accessToken
        return if(accessToken.isNotBlank()) "%s %s".format(BEARER,accessToken) else ""
    }

}