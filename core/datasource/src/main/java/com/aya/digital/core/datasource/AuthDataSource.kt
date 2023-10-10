package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.auth.LoginResponse
import com.aya.digital.core.network.model.response.auth.RegistrationResponse
import com.aya.digital.core.network.model.response.auth.UserKeyResponse
import com.aya.digital.core.network.model.response.auth.VerifiedResponse
import io.reactivex.rxjava3.core.Single

interface AuthDataSource {

    fun registerProfile(registrationBody: RegistrationBody): Single<RegistrationResponse>

    fun generateToken(loginBody: LoginBody): Single<LoginResponse>

    fun verifyProfile(code: String): Single<Boolean>

    fun sendCode(login: String): Single<Unit>

    fun getUserKey(code: String): Single<UserKeyResponse>

    fun resetPassword(userKey: String, resetPasswordBody: ResetPasswordBody): Single<Boolean>

    fun changePassword(changePasswordBody: ChangePasswordBody): Single<Boolean>

    fun changeEmail(code: String, changeEmailBody: ChangeEmailBody): Single<Boolean>

    fun checkIsVerified(email:String):Single<VerifiedResponse>
}