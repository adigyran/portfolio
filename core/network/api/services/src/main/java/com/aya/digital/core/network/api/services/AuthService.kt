package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.auth.LoginResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface AuthService {

    @POST("base/register-profile/")
    fun registerProfile(@Body registrationBody: RegistrationBody): Completable

    @POST("base/generate-token/")
    fun generateToken(@Body loginBody: LoginBody): Single<LoginResponse>

    @POST("verify-operations/verify-profile/")
    fun verifyProfile(@Query("codeInput") code: String): Completable

    @POST("verify-operations/send-code/")
    fun sendCode(@Query("login") login: String): Completable

    @POST("credentials/reset-by-code/")
    fun resetPassword(@Query("codeVerify") code:String, @Body resetPasswordBody: ResetPasswordBody): Completable

}