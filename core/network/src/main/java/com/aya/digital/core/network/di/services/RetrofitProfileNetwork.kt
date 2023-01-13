package com.aya.digital.core.network.di.services

import com.aya.digital.core.network.api.services.ProfileService
import com.aya.digital.core.network.datasources.ProfileDataSource
import com.aya.digital.core.network.datasources.ProfilePatientDataSource
import com.aya.digital.core.network.datasources.ProfilePractitionerDataSource
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

fun profilePractitionerModule() = DI.Module("profilePractitionerModule") {
    bind<ProfilePractitionerDataSource>() with eagerSingleton {
        val apiService = createApiService<ProfileService>(instance())
        return@eagerSingleton RetrofitProfilePractitionerNetwork(apiService)
    }
}

fun profilePatientModule() = DI.Module("profilePatientModule") {
    bind<ProfilePatientDataSource>() with eagerSingleton {
        val apiService = createApiService<ProfileService>(instance())
        return@eagerSingleton RetrofitProfilePatientNetwork(apiService)
    }
}

class RetrofitProfileNetwork(private val network: ProfileService) : ProfileDataSource {

    override fun currentProfile(): Single<CurrentProfileResponse> = network.currentProfile()

    override fun updateProfile(body: ProfileBody): Completable = network.updateProfile(body)

    override fun getEmergencyContact(): Single<EmergencyContactResponse> = network.getEmergencyContact()

    override fun updateEmergencyContact(body: EmergencyContactBody): Completable = network.updateEmergencyContact(body)

    override fun uploadAvatar(file: MultipartBody.Part): Single<ImageUploadResponse> = network.uploadAvatar(file)

    override fun deleteAvatar(): Completable = network.deleteAvatar()

    override fun registration(body: RegistrationBody): Single<Message> = network.registration(body)

    override fun logout(clientId: String, refreshToken: String): Completable = network.logout(clientId, refreshToken)
}

class RetrofitProfilePractitionerNetwork(private val network: ProfileService) : ProfilePractitionerDataSource
{
    override fun currentPractitioner(): Single<PractitionerProfileResponse> = network.currentPractitioner()

    override fun getPractitionerPhoneNumber(): Single<PractitionerProfileResponse> = network.getPractitionerPhoneNumber()

    override fun updatePractitioner(body: PractitionerProfileBody): Completable = network.updatePractitioner(body)

    override fun updatePractitionerPhoneNumber(body: PractitionerProfileBody): Completable = network.updatePractitionerPhoneNumber(body)

    override fun updatePractitionerAddress(body: PractitionerProfileBody): Completable = network.updatePractitionerAddress(body)

    override fun getPractitionerAddress(): Single<AddressResponse>  = network.getPractitionerAddress()
}

class RetrofitProfilePatientNetwork(private val network: ProfileService) : ProfilePatientDataSource
{
    override fun currentPatient(): Single<PatientProfileResponse> = network.currentPatient()

    override fun updatePatient(body: PatientProfileBody): Completable = network.updatePatient(body)

    override fun getPatientAddress(): Single<AddressResponse> = network.getPatientAddress()

    override fun updatePatientAddress(body: PatientProfileBody): Completable = network.updatePatientAddress(body)

}