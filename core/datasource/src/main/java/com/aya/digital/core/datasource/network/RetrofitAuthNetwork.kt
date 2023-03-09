package com.aya.digital.core.datasource.network

import com.aya.digital.core.datasource.AuthDataSource
import com.aya.digital.core.network.api.services.AuthService
import com.aya.digital.core.network.main.di.modules.createApiService
import com.aya.digital.core.network.main.RetrofitTags
import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.auth.LoginResponse
import com.aya.digital.core.network.model.response.auth.RegistrationResponse
import com.aya.digital.core.network.model.response.auth.UserKeyResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton


fun authNetworkModule() = DI.Module("authNetworkModule") {
    bind<AuthDataSource>() with singleton {
        val apiService =
            createApiService<AuthService>(instance(RetrofitTags.RETROFIT_AUTH_TAG))
        return@singleton RetrofitAuthNetwork(apiService)
    }
}

class RetrofitAuthNetwork(private val network: AuthService) :
    AuthDataSource {
    override fun registerProfile(registrationBody: RegistrationBody): Single<RegistrationResponse> =
        network.registerProfile(registrationBody)

    override fun generateToken(loginBody: LoginBody): Single<LoginResponse> =
        network.generateToken(loginBody)

    override fun verifyProfile(code: String): Single<Boolean> = network.verifyProfile(code)
    override fun sendCode(login: String): Single<Unit> = network.sendCode(login)

    override fun getUserKey(code: String): Single<UserKeyResponse> = network.getUserKey(code)

    override fun resetPassword(
        userKey: String,
        resetPasswordBody: ResetPasswordBody
    ): Single<Boolean> = network.resetPassword(userKey, resetPasswordBody)

    override fun changePassword(changePasswordBody: ChangePasswordBody): Single<Boolean> =
        network.changePassword(changePasswordBody)

    override fun changeEmail(code: String, changeEmailBody: ChangeEmailBody): Single<Boolean> =
        network.changeEmail(code, changeEmailBody)


}
