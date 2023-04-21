package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.auth.LoginResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface TokenService {

    @FormUrlEncoded
    @POST("base/refresh-token")
    fun refreshToken(@Field("refresh_token") refreshToken:String): Single<LoginResponse>
    @FormUrlEncoded
    @POST("base/logout")
    fun logout(@Field("refresh_token") refreshToken: String): Completable
}