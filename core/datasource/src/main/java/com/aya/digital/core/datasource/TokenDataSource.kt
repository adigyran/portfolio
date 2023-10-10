package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.auth.LoginResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface TokenDataSource {

    fun refreshToken(refreshTokenBody: RefreshTokenBody): Single<LoginResponse>

    fun logout(logoutBody: LogoutBody): Completable
}