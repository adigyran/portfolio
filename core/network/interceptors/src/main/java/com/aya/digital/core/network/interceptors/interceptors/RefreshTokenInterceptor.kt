package com.aya.digital.core.network.interceptors.interceptors

import android.util.Log
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.datastore.HealthAppAuthDataSource
import com.aya.digital.core.network.interceptors.InterceptorsConstants.AUTHORIZATION
import com.aya.digital.core.network.interceptors.ext.accessTokenBlocking
import com.aya.digital.core.network.interceptors.ext.bearerTokenBlocking
import com.aya.digital.core.network.interceptors.ext.refreshTokenBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class RefreshTokenInterceptor(
    private val authDataSource: HealthAppAuthDataSource,
    private val authRepository: AuthRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequestTimestamp = System.currentTimeMillis()
        val originalResponse = chain.proceed(chain.request())
        return originalResponse
            .takeIf { it.code != 401 }
            ?: handleUnauthorizedResponse(chain, originalResponse, originalRequestTimestamp)
    }

    private fun handleUnauthorizedResponse(
        chain: Interceptor.Chain,
        originalResponse: Response,
        requestTimestamp: Long
    ): Response {
        val latch = getLatch()
        return when {
            latch != null && latch.count > 0 -> handleTokenIsUpdating(
                chain,
                latch,
                requestTimestamp
            )
                ?: originalResponse
            tokenUpdateTime > requestTimestamp -> updateTokenAndProceedChain(chain)
            else -> handleTokenNeedRefresh(chain) ?: originalResponse
        }
    }

    private fun handleTokenIsUpdating(
        chain: Interceptor.Chain,
        latch: CountDownLatch,
        requestTimestamp: Long
    ): Response? {
        return if (latch.await(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            && tokenUpdateTime > requestTimestamp
        ) {
            updateTokenAndProceedChain(chain)
        } else {
            null
        }
    }

    private fun handleTokenNeedRefresh(
        chain: Interceptor.Chain
    ): Response? {
        return if (refreshToken()) {
            updateTokenAndProceedChain(chain)
        } else {
            null
        }
    }

    private fun refreshToken(): Boolean {
        initLatch()
        val tokenRefreshed =
            authRepository.refreshAndSaveTokens(authDataSource.refreshTokenBlocking())
                .blockingGet()
                .processResult({ it }, {
                    Log.d(
                        RefreshTokenInterceptor::class.java.name,
                        "refreshToken: ${it.toString()}"
                    )
                    false
                })
        getLatch()?.countDown()
        return tokenRefreshed
    }

    private fun updateTokenAndProceedChain(
        chain: Interceptor.Chain
    ): Response {
        val newRequest = updateOriginalCallWithNewToken(chain.request())
        return chain.proceed(newRequest)
    }


    private fun updateOriginalCallWithNewToken(request: Request): Request {
        val accessToken = authDataSource.accessTokenBlocking()
        return if (accessToken.isNotEmpty()) {
            request
                .newBuilder()
                .header(AUTHORIZATION, authDataSource.bearerTokenBlocking())
                .build()
        } else request
    }


    companion object {

        private const val REQUEST_TIMEOUT = 30L

        @Volatile
        private var tokenUpdateTime: Long = 0L

        private var countDownLatch: CountDownLatch? = null

        @Synchronized
        fun initLatch() {
            countDownLatch = CountDownLatch(1)
        }

        @Synchronized
        fun getLatch() = countDownLatch
    }
}