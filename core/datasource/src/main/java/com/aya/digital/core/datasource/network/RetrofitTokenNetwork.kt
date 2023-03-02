package com.aya.digital.core.datasource.network

import com.aya.digital.core.datasource.*
import com.aya.digital.core.network.api.services.AuthService
import com.aya.digital.core.network.api.services.ProfileService
import com.aya.digital.core.network.api.services.TokenService
import com.aya.digital.core.network.di.createApiService
import com.aya.digital.core.network.main.RetrofitTags
import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.EmergencyContactResponse
import com.aya.digital.core.network.model.response.MessageResponse
import com.aya.digital.core.network.model.response.auth.LoginResponse
import com.aya.digital.core.network.model.response.doctor.PractitionerProfileResponse
import com.aya.digital.core.network.model.response.patient.PatientProfileResponse
import com.aya.digital.core.network.model.response.profile.AddressResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.network.model.response.profile.ImageUploadResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
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
    override fun refreshToken(refreshTokenBody: RefreshTokenBody): Single<LoginResponse> = network.refreshToken(refreshTokenBody)
    override fun logout(logoutBody: LogoutBody): Single<Void> = network.logout(logoutBody)


}
