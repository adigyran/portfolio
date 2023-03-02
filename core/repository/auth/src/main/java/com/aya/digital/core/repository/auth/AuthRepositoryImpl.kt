package com.aya.digital.core.repository.auth

import com.aya.digital.core.data.mappers.profile.LoginResultMapper
import com.aya.digital.core.data.profile.LoginResult
import com.aya.digital.core.data.profile.repository.AuthRepository
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

internal class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource,
    private val loginResultMapper: LoginResultMapper,
    private val authDataStore: HealthAppAuthDataSource,
    private val tokenDataSource: TokenDataSource
) : AuthRepository {
    override fun checkIfTokenPresent(): Single<RequestResult<Boolean>> =
        authDataStore.refreshTokenData
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .userDataResponseToResult()
            .mapResult({ it.isNotBlank().asResult() }, { it })
            .firstOrError()


    override fun generateToken(
        email: String,
        password: String
    ): Single<RequestResult<LoginResult>> =
        authDataSource.generateToken(LoginBody(email, password))
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ loginResultMapper.mapFrom(it).asResult() }, { it })

    override fun registerProfile(
        email: String,
        firstName: String,
        lastName: String,
        insurances: List<Int>,
        password: String,
        passwordConfirm: String
    ): Single<RequestResult<Boolean>> =
        authDataSource.registerProfile(
            RegistrationBody(
                email,
                firstName,
                lastName,
                email,
                null,
                password,
                passwordConfirm,
                insurances
            )
        )
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ true.asResult() }, { it })

    override fun saveToken(token: String, refreshToken: String): Single<RequestResult<Boolean>> =
        authDataStore.saveAuthData(token, refreshToken)
            .map { true.asResult() }

    override fun verifyCode(code: String): Single<RequestResult<Boolean>> =
        authDataSource.verifyProfile(code)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ it.asResult() }, { it })

    override fun refreshAndSaveTokens(refreshToken: String): Single<RequestResult<Boolean>> =
        tokenDataSource.refreshToken(
            RefreshTokenBody(
                clientId = "mobile-client",
                grantType = "refresh_token",
                clientSecret = "JXnfDtUdxow13VyKu4771WjCOyyijeWG",
                refreshToken = refreshToken
            )
        )
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ loginResultMapper.mapFrom(it).asResult() }, { it })
            .flatMapResult({
                if (it.token.isNotBlank() && it.refreshToken.isNotBlank()) saveToken(it.token,it.refreshToken) else Single.just(false.asResult())},{Single.just(it)})

}