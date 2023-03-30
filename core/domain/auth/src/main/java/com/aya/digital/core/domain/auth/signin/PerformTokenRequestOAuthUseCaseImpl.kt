package com.aya.digital.core.domain.auth.signin

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.data.profile.repository.AuthTokenRepository
import net.openid.appauth.AuthorizationService
import net.openid.appauth.TokenRequest

class PerformTokenRequestOAuthUseCaseImpl(
    private val authTokenRepository: AuthTokenRepository,
    private val authRepository: AuthRepository
) : PerformTokenRequestOAuthUseCase {
    override suspend fun invoke(
        authService: AuthorizationService,
        tokenRequest: TokenRequest
    ): RequestResultModel<Boolean> =
        authTokenRepository.performTokenRequest(authService, tokenRequest).processResult({
            authTokenRepository.saveOAuthToken(it.accessToken, it.refreshToken)
            true.asResultModel()
        }, { it.toModelError() })
}