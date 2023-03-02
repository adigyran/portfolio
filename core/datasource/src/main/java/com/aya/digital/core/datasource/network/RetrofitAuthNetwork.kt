package com.aya.digital.core.datasource.network

import com.aya.digital.core.datasource.AuthDataSource
import com.aya.digital.core.datasource.ProfileDataSource
import com.aya.digital.core.datasource.ProfilePatientDataSource
import com.aya.digital.core.datasource.ProfilePractitionerDataSource
import com.aya.digital.core.network.api.services.AuthService
import com.aya.digital.core.network.api.services.ProfileService
import com.aya.digital.core.network.di.createApiService
import com.aya.digital.core.network.main.RetrofitTags
import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.EmergencyContactResponse
import com.aya.digital.core.network.model.response.MessageResponse
import com.aya.digital.core.network.model.response.auth.LoginResponse
import com.aya.digital.core.network.model.response.auth.RegistrationResponse
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
