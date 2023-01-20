package com.aya.digital.core.data.repositories.profile

import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.EndSessionRequest
import net.openid.appauth.TokenRequest

interface AuthRepository {
    fun getAuthRequest(): AuthorizationRequest

    fun getEndSessionRequest(): EndSessionRequest

    fun isPatient(): Single<RequestResult<Boolean>>

    fun performTokenRequest(tokenRequest: TokenRequest): Completable

    fun clear()

    fun logout(): Completable
}