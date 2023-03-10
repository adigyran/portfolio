package com.aya.digital.core.datasource

import android.provider.ContactsContract.CommonDataKinds.Email
import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.EmergencyContactResponse
import com.aya.digital.core.network.model.response.auth.LoginResponse
import com.aya.digital.core.network.model.response.auth.RegistrationResponse
import com.aya.digital.core.network.model.response.auth.UserKeyResponse
import com.aya.digital.core.network.model.response.auth.VerifiedResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.network.model.response.profile.ImageUploadResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody

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