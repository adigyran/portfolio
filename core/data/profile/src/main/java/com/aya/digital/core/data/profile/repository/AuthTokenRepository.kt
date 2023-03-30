package com.aya.digital.core.data.profile.repository

import com.aya.digital.core.network.model.auth.Tokens
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.TokenRequest

interface AuthTokenRepository {

    fun refreshAndSaveTokens(): Single<RequestResult<Boolean>>
    fun getAuthRequest():  Single<RequestResult<AuthorizationRequest>>

    suspend fun performTokenRequest(
        authService: AuthorizationService,
        tokenRequest: TokenRequest,
    ): RequestResult<Tokens>

    suspend fun saveOAuthToken(token: String, refreshToken: String): RequestResult<Boolean>

}