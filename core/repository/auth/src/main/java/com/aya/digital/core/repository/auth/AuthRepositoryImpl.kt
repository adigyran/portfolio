package com.aya.digital.core.repository.auth

import com.aya.digital.core.data.mappers.profile.LoginResultMapper
import com.aya.digital.core.data.profile.mappers.UserKeyResultMapper
import com.aya.digital.core.data.profile.LoginResult
import com.aya.digital.core.data.profile.UserKeyResult
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.datasource.AuthDataSource
import com.aya.digital.core.datastore.HealthAppAuthDataSource
import com.aya.digital.core.ext.*
import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

internal class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource,
    private val loginResultMapper: LoginResultMapper,
    private val authDataStore: HealthAppAuthDataSource,
    private val userKeyResultMapper: UserKeyResultMapper
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

    override fun getRestoreCode(email: String): Single<RequestResult<Boolean>>  =
        authDataSource.sendCode(email)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ true.asResult() }, { it })

    override fun getRestoreToken(code: String): Single<RequestResult<UserKeyResult>> =
        authDataSource.getUserKey(code)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ userKeyResultMapper.mapFrom(it).asResult() }, { it })

    override fun restorePassword(
        token: String,
        resetPasswordBody: ResetPasswordBody
    ): Single<RequestResult<Boolean>>  =
        authDataSource.resetPassword(token,resetPasswordBody)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ it.asResult() }, { it })
    override fun changePassword(changePasswordBody: ChangePasswordBody): Single<RequestResult<Boolean>> =
        authDataSource.changePassword(changePasswordBody)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ it.asResult() }, { it })

    override fun changeEmailSendCode(email: String): Single<RequestResult<Boolean>> =
        authDataSource.sendCode(email)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ true.asResult() }, { it })

    override fun changeEmail(code: String, email: String): Single<RequestResult<Boolean>> =
        authDataSource.changeEmail(code,ChangeEmailBody(email))
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ it.asResult() }, { it })

    override fun checkIsVerified(email: String): Single<RequestResult<Boolean>> =
        authDataSource.checkIsVerified(email)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ it.verified.asResult() }, { it })

}