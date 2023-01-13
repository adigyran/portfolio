package com.aya.digital.core.repository.profile

import com.aya.digital.core.data.repositories.profile.AuthRepository
import com.aya.digital.core.network.di.AppAuth
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.EndSessionRequest

class AuthRepositoryImpl(
    private val appAuth: AppAuth
) : AuthRepository {
    override fun getAuthRequest(): AuthorizationRequest {
        TODO("Not yet implemented")
    }

    override fun getEndSessionRequest(): EndSessionRequest {
        TODO("Not yet implemented")
    }

    override fun isPatient(): Single<Boolean> {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun logout(): Completable {
        TODO("Not yet implemented")
    }
}