package com.aya.digital.core.data.profile.repository

import com.aya.digital.core.data.profile.LoginResult
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single

interface AuthRepository {

    fun checkIfTokenPresent():Single<RequestResult<Boolean>>
    fun generateToken(email: String, password: String): Single<RequestResult<LoginResult>>

    fun registerProfile(
        email: String,
        firstName: String,
        lastName: String,
        insurances: List<Int>,
        password: String,
        passwordConfirm: String
    ): Single<RequestResult<Boolean>>

    fun saveToken(token: String, refreshToken: String): Single<RequestResult<Boolean>>

    fun verifyCode(code: String) : Single<RequestResult<Boolean>>
    fun refreshAndSaveTokens(refreshToken: String): Single<RequestResult<Boolean>>
}