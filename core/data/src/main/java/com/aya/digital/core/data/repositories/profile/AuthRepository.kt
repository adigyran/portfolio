package com.aya.digital.core.data.repositories.profile

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.EndSessionRequest

interface AuthRepository {
    fun getAuthRequest(): AuthorizationRequest
    fun getEndSessionRequest(): EndSessionRequest
    fun isPatient(): Single<Boolean>
    fun clear()
    fun logout(): Completable

}