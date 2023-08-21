package com.aya.digital.core.repository.profile.main

import android.content.Context
import android.net.Uri
import com.aya.digital.core.data.dictionaries.mappers.InsuranceCompanyMapper
import com.aya.digital.core.data.mappers.profile.InsurancePolicyMapper
import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.data.profile.EmergencyContact
import com.aya.digital.core.data.profile.ImageUploadResult
import com.aya.digital.core.data.profile.InsurancePolicyModel
import com.aya.digital.core.data.profile.NotificationsStatus
import com.aya.digital.core.data.profile.mappers.AvatarMapper
import com.aya.digital.core.data.profile.mappers.CurrentProfileMapper
import com.aya.digital.core.data.profile.mappers.EmergencyContactMapper
import com.aya.digital.core.data.profile.mappers.ImageUploadResultMapper
import com.aya.digital.core.data.profile.mappers.NotificationsStatusMapper
import com.aya.digital.core.data.profile.repository.ProfileInsuranceRepository
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.datasource.DictionariesDataSource
import com.aya.digital.core.datasource.ProfileDataSource
import com.aya.digital.core.datasource.TokenDataSource
import com.aya.digital.core.datastore.HealthAppAuthDataSource
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.ext.retrofitResponseToResult
import com.aya.digital.core.ext.retryOnError
import com.aya.digital.core.navigation.events.invalidToken.InvalidTokenEventManager
import com.aya.digital.core.network.model.request.EmergencyContactBody
import com.aya.digital.core.network.model.request.InsurancePolicyBody
import com.aya.digital.core.network.model.request.LogoutBody
import com.aya.digital.core.network.model.request.NotificationSettingsBody
import com.aya.digital.core.network.model.request.ProfileBody
import com.aya.digital.core.network.model.request.UpdatePhoneBody
import com.aya.digital.core.network.model.response.profile.ImageUploadResponse
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

internal class ProfileInsuranceRepositoryImpl(
    private val profileDataSource: ProfileDataSource,
    private val insuranceMapper: InsurancePolicyMapper,
) :
    ProfileInsuranceRepository {


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

    override fun getInsurances(): Observable<RequestResult<List<InsurancePolicyModel>>> =
        profileDataSource.getInsurances()
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ insurancePolicyResponses ->
                insuranceMapper.mapFromList(
                    insurancePolicyResponses
                ).asResult()
            }, { it })

    override fun getInsuranceById(insuranceId: Int): Single<RequestResult<InsurancePolicyModel>> =
        profileDataSource.getInsuranceById(insuranceId)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ insurancePolicyResponse ->
                insuranceMapper.mapFrom(insurancePolicyResponse).asResult()
            }, { it })


    override fun deleteInsurance(insuranceId: Int): Single<RequestResult<Boolean>> =
        profileDataSource.deleteInsurance(insuranceId)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({
                true.asResult()
            }, { it })

}