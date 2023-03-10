package com.aya.digital.core.repository.profile

import com.aya.digital.core.data.mappers.profile.InsurancePolicyMapper
import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.data.profile.EmergencyContact
import com.aya.digital.core.data.profile.ImageUploadResult
import com.aya.digital.core.data.profile.InsurancePolicyModel
import com.aya.digital.core.data.profile.mappers.CurrentProfileMapper
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.datasource.ProfileDataSource
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.ext.retrofitResponseToResult
import com.aya.digital.core.ext.retryOnError
import com.aya.digital.core.network.model.request.EmergencyContactBody
import com.aya.digital.core.network.model.request.InsuranceBody
import com.aya.digital.core.network.model.request.InsurancePolicyBody
import com.aya.digital.core.network.model.request.ProfileBody
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.io.File

internal class ProfileRepositoryImpl(
    private val profileDataSource: ProfileDataSource,
    private val currentProfileMapper: CurrentProfileMapper,
    private val insuranceMapper: InsurancePolicyMapper
) :
    ProfileRepository {
    override fun currentProfileId(): Single<RequestResult<Int>> {
        TODO("Not yet implemented")
    }

    override fun currentProfile(): Single<RequestResult<CurrentProfile>> =
        profileDataSource.currentProfile()
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({
                currentProfileMapper.mapFrom(it).asResult()
            }, { it })

    override fun updateProfile(body: ProfileBody): Single<RequestResult<CurrentProfile>> =
        profileDataSource.updateProfile(body)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({
                currentProfileMapper.mapFrom(it).asResult()
            }, { it })

    override fun getEmergencyContact(): Single<RequestResult<EmergencyContact>> {
        TODO("Not yet implemented")
    }

    override fun updateEmergencyContact(body: EmergencyContactBody): Single<RequestResult<Unit>> {
        TODO("Not yet implemented")
    }

    override fun uploadImage(file: File): RequestResult<ImageUploadResult> {
        TODO("Not yet implemented")
    }


    override fun addInsurance(insurancePolicyBody: InsurancePolicyBody): Single<RequestResult<Boolean>> =
        profileDataSource.addInsurance(insurancePolicyBody)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({
                true.asResult()
            }, { it })

    override fun saveInsurance(
        insuranceId: Int,
        insurancePolicyBody: InsurancePolicyBody
    ): Single<RequestResult<Boolean>> =
        profileDataSource.saveInsurance(insuranceId, insurancePolicyBody)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({
                true.asResult()
            }, { it })

    override fun getInsurances(): Observable<RequestResult<Boolean>> =
        profileDataSource.getInsurances()
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({
                true.asResult()
            }, { it })

    override fun getInsuranceById(insuranceId: Int): Single<RequestResult<InsurancePolicyModel>> =
        profileDataSource.getInsuranceById(insuranceId)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({insuranceMapper.mapFrom(it).asResult()
            }, { it })

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun updatePhoneNumber(number: String): Single<RequestResult<Unit>> {
        TODO("Not yet implemented")
    }

}