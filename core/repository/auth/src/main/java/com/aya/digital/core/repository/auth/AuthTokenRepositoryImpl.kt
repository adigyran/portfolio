package com.aya.digital.core.repository.auth

import com.aya.digital.core.data.mappers.profile.LoginResultMapper
import com.aya.digital.core.data.profile.LoginResult
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.data.profile.repository.AuthTokenRepository
import com.aya.digital.core.datasource.AuthDataSource
import com.aya.digital.core.datasource.TokenDataSource
import com.aya.digital.core.datastore.HealthAppAuthDataSource
import com.aya.digital.core.ext.*
import com.aya.digital.core.network.model.request.LoginBody
import com.aya.digital.core.network.model.request.RefreshTokenBody
import com.aya.digital.core.network.model.request.RegistrationBody
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

internal class AuthTokenRepositoryImpl(
    private val authDataStore: HealthAppAuthDataSource,
    private val tokenDataSource: TokenDataSource,
    private val loginResultMapper: LoginResultMapper,
) : AuthTokenRepository {


    override fun refreshAndSaveTokens(): Single<RequestResult<Boolean>> =
        getRefreshToken()
            .flatMapResult({refreshToken ->
                           if(refreshToken.isNotBlank()) {
                               tokenDataSource.refreshToken(RefreshTokenBody(refreshToken = refreshToken))
                                   .retryOnError()
                                   .retrofitResponseToResult(CommonUtils::mapServerErrors)
                                   .mapResult({ loginResultMapper.mapFrom(it).asResult() }, { it })
                                   .flatMapResult({
                                       if (it.token.isNotBlank() && it.refreshToken.isNotBlank()) saveToken(
                                           it.token,
                                           it.refreshToken
                                       ) else Single.just(false.asResult())
                                   }, { Single.just(it) })
                           } else Single.just(false.asResult())
            },{Single.just(it)})

    private fun getRefreshToken() : Single<RequestResult<String>> =
        authDataStore.refreshTokenData
            .firstOrError()
            .map { it.asResult() }
            .doOnError {it.asErrorResult<String>() }

    private fun saveToken(token: String, refreshToken: String): Single<RequestResult<Boolean>> =
        authDataStore.saveAuthData(token, refreshToken)
            .map { true.asResult() }
}