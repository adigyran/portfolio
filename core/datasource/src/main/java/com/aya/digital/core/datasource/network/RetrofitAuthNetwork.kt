package com.aya.digital.core.datasource.network

import com.aya.digital.core.datasource.AuthDataSource
import com.aya.digital.core.network.api.services.AuthService
import com.aya.digital.core.network.main.di.modules.createApiService
import com.aya.digital.core.network.main.RetrofitTags
import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.auth.LoginResponse
import com.aya.digital.core.network.model.response.auth.RegistrationResponse
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
    override fun registerProfile(registrationBody: RegistrationBody): Single<RegistrationResponse> = network.registerProfile(registrationBody)

    override fun generateToken(loginBody: LoginBody): Single<LoginResponse> = network.generateToken(loginBody)

    override fun verifyProfile(code: String): Single<Boolean>  = network.verifyProfile(code)

    override fun sendCode(login: String): Completable = network.sendCode(login)

    override fun resetPassword(code: String, resetPasswordBody: ResetPasswordBody): Completable = network.resetPassword(code, resetPasswordBody)
}
