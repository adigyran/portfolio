package com.aya.digital.core.domain.auth.signin.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AuthTokenRepository
import com.aya.digital.core.domain.auth.signin.SignInOAuthUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single
import net.openid.appauth.AuthorizationRequest

internal class SignInOAuthUseCaseImpl(private val authTokenRepository: AuthTokenRepository) : SignInOAuthUseCase {
    override fun invoke(): Single<RequestResultModel<AuthorizationRequest>> =
        authTokenRepository.getAuthRequest()
            .mapResult({it.asResultModel()},{it.toModelError()})
}