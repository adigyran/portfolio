package com.aya.digital.core.network.di.services

import com.aya.digital.core.network.api.services.ProfileService
import com.aya.digital.core.network.datasources.ProfileDataSource
import com.aya.digital.core.network.di.createApiService
import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.EmergencyContactResponse
import com.aya.digital.core.network.model.response.Message
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
import org.kodein.di.eagerSingleton
import org.kodein.di.instance


fun profileNetworkModule() = DI.Module("profileNetworkModule") {
    bind<ProfileDataSource>() with eagerSingleton {
        val apiService = createApiService<ProfileService>(instance())
        return@eagerSingleton RetrofitProfileNetwork(apiService)
    }
}

class RetrofitProfileNetwork(network: ProfileService) : ProfileDataSource {

    override fun currentProfile(): Single<CurrentProfileResponse> {
        TODO("Not yet implemented")
    }

    override fun updateProfile(body: ProfileBody): Completable {
        TODO("Not yet implemented")
    }

    override fun currentPatient(): Single<PatientProfileResponse> {
        TODO("Not yet implemented")
    }

    override fun updatePatient(body: PatientProfileBody): Completable {
        TODO("Not yet implemented")
    }

    override fun getPatientAddress(): Single<AddressResponse> {
        TODO("Not yet implemented")
    }

    override fun updatePatientAddress(body: PatientProfileBody): Completable {
        TODO("Not yet implemented")
    }

    override fun getEmergencyContact(): Single<EmergencyContactResponse> {
        TODO("Not yet implemented")
    }

    override fun updateEmergencyContact(body: EmergencyContactBody): Completable {
        TODO("Not yet implemented")
    }

    override fun currentPractitioner(): Single<PractitionerProfileResponse> {
        TODO("Not yet implemented")
    }

    override fun getPractitionerPhoneNumber(): Single<PractitionerProfileResponse> {
        TODO("Not yet implemented")
    }

    override fun updatePractitioner(body: PractitionerProfileBody): Completable {
        TODO("Not yet implemented")
    }

    override fun updatePractitionerPhoneNumber(body: PractitionerProfileBody): Completable {
        TODO("Not yet implemented")
    }

    override fun updatePractitionerAddress(body: PractitionerProfileBody): Completable {
        TODO("Not yet implemented")
    }

    override fun getPractitionerAddress(): Single<AddressResponse> {
        TODO("Not yet implemented")
    }

    override fun uploadAvatar(file: MultipartBody.Part): Single<ImageUploadResponse> {
        TODO("Not yet implemented")
    }

    override fun deleteAvatar(): Completable {
        TODO("Not yet implemented")
    }

    override fun registration(body: RegistrationBody): Single<Message> {
        TODO("Not yet implemented")
    }

    override fun logout(clientId: String, refreshToken: String): Completable {
        TODO("Not yet implemented")
    }
}