package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.auth.LoginResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface TokenService {

    @POST("openid-connect/logout")
    fun refreshToken(@Body refreshTokenBody: RefreshTokenBody): Completable

    @POST("openid-connect/logout")
    fun logout(@Body logoutBody: LogoutBody): Completable
}