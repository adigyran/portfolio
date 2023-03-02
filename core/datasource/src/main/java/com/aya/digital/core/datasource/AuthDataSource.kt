package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.EmergencyContactResponse
import com.aya.digital.core.network.model.response.auth.LoginResponse
import com.aya.digital.core.network.model.response.auth.RegistrationResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.network.model.response.profile.ImageUploadResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody

interface AuthDataSource {

    fun registerProfile(registrationBody: RegistrationBody): Single<RegistrationResponse>

    fun generateToken(loginBody: LoginBody): Single<LoginResponse>

    fun verifyProfile(code: String): Single<Boolean>

    fun sendCode(login: String): Completable

    fun resetPassword(code:String, resetPasswordBody: ResetPasswordBody): Completable

}