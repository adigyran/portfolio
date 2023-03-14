package com.aya.digital.core.datasource.network

import com.aya.digital.core.datasource.ProfileDataSource
import com.aya.digital.core.datasource.ProfilePatientDataSource
import com.aya.digital.core.datasource.ProfilePractitionerDataSource
import com.aya.digital.core.network.api.services.ProfileService
import com.aya.digital.core.network.main.di.modules.createApiService
import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.EmergencyContactResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.network.model.response.profile.ImageUploadResponse
import com.aya.digital.core.network.model.response.profile.InsuranceCompanyResponse
import com.aya.digital.core.network.model.response.profile.InsurancePolicyResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton


fun profileNetworkModule() = DI.Module("profileNetworkModule") {
    bind<ProfileDataSource>() with singleton {
        val apiService =
            createApiService<ProfileService>(instance())
        return@singleton RetrofitProfileNetwork(apiService)
    }
}

fun profilePractitionerModule() = DI.Module("profilePractitionerModule") {
    bind<ProfilePractitionerDataSource>() with singleton {
        val apiService =
            createApiService<ProfileService>(instance())
        return@singleton RetrofitProfilePractitionerNetwork(apiService)
    }
}

fun profilePatientModule() = DI.Module("profilePatientModule") {
    bind<ProfilePatientDataSource>() with singleton {
        val apiService =
            createApiService<ProfileService>(instance())
        return@singleton RetrofitProfilePatientNetwork(apiService)
    }
}

class RetrofitProfileNetwork(private val network: ProfileService) :
    ProfileDataSource {

    override fun currentProfile(): Single<CurrentProfileResponse> =
        network.currentProfile()

    override fun updateProfile(body: ProfileBody): Single<CurrentProfileResponse> =
        network.updateProfile(body)

    override fun getEmergencyContact(): Single<EmergencyContactResponse> =
        network.getEmergencyContact()

    override fun updateEmergencyContact(body: EmergencyContactBody): Completable =
        network.updateEmergencyContact(body)

    override fun uploadImage(file: MultipartBody.Part): Single<ImageUploadResponse> = network.uploadImage(file)
    override fun addInsurance(insurancePolicyBody: InsurancePolicyBody): Single<Unit>  = network.addInsurance(insurancePolicyBody)

    override fun saveInsurance(insuranceId: Int, insurancePolicyBody: InsurancePolicyBody): Single<Unit> = network.saveInsurance(insuranceId, insurancePolicyBody)

    override fun getInsurances(): Observable<List<InsurancePolicyResponse>> = network.getInsurances()

    override fun getInsuranceById(insuranceId: Int): Single<InsurancePolicyResponse> = network.getInsuranceById(insuranceId)
    override fun deleteInsurance(insuranceId: Int): Single<Unit> = network.deleteInsurance(insuranceId)
    override fun getAttachmentById(attachmentId: Int): Single<Unit> = network.getAttachmentById(attachmentId)
}

class RetrofitProfilePractitionerNetwork(private val network: ProfileService) :
    ProfilePractitionerDataSource {

}

class RetrofitProfilePatientNetwork(private val network: ProfileService) :
    ProfilePatientDataSource {


}