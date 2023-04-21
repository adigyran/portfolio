package com.aya.digital.core.datasource.network

import com.aya.digital.core.datasource.*
import com.aya.digital.core.network.api.services.TokenService
import com.aya.digital.core.network.main.di.modules.createApiService
import com.aya.digital.core.network.main.RetrofitTags
import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.auth.LoginResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton


fun tokenNetworkModule() = DI.Module("tokenNetworkModule") {
    bind<TokenDataSource>() with singleton {
        val apiService =
            createApiService<TokenService>(instance(RetrofitTags.RETROFIT_TOKEN_TAG))
        return@singleton RetrofitTokenNetwork(apiService)
    }
}

class RetrofitTokenNetwork(private val network: TokenService) :
    TokenDataSource {
    override fun refreshToken(refreshTokenBody: RefreshTokenBody): Single<LoginResponse> =
        network.refreshToken(refreshTokenBody.refreshToken)

    override fun logout(logoutBody: LogoutBody): Completable = network.logout(logoutBody.refreshToken)


}
