package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.auth.LoginResponse
import com.aya.digital.core.network.model.response.auth.RegistrationResponse
import com.aya.digital.core.network.model.response.auth.UserKeyResponse
import com.aya.digital.core.network.model.response.auth.VerifiedResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface AuthService {

    @POST("base/register-profile")
    fun registerProfile(@Body registrationBody: RegistrationBody): Single<RegistrationResponse>

    @POST("base/generate-token")
    fun generateToken(@Body loginBody: LoginBody): Single<LoginResponse>

    @GET("verify-operations/verify-profile")
    fun verifyProfile(@Query("codeInput") code: String): Single<Boolean>

    @POST("verify-operations/send-code")
    fun sendCode(@Query("login") login: String): Single<Unit>

    @GET("verify-operations/get-user-key")
    fun getUserKey(@Query("codeInput") code: String): Single<UserKeyResponse>

    @POST("base/forget-password-userkey")
    fun resetPassword(@Header("userkey") userKey: String, @Body resetPasswordBody: ResetPasswordBody): Single<Boolean>

    @POST("base/change-password")
    fun changePassword(@Body changePasswordBody: ChangePasswordBody): Single<Boolean>
    @POST("base/change-email")
    fun changeEmail(@Query("codeVerify") code: String, @Body changeEmailBody: ChangeEmailBody): Single<Boolean>

    @GET("base/check-verified")
    fun checkIsVerified(@Query("login") login: String): Single<VerifiedResponse>

}