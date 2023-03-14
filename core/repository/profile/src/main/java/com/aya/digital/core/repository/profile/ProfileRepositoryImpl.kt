package com.aya.digital.core.repository.profile

import com.aya.digital.core.data.dictionaries.mappers.InsuranceCompanyMapper
import com.aya.digital.core.data.mappers.profile.InsurancePolicyMapper
import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.data.profile.EmergencyContact
import com.aya.digital.core.data.profile.ImageUploadResult
import com.aya.digital.core.data.profile.InsurancePolicyModel
import com.aya.digital.core.data.profile.mappers.CurrentProfileMapper
import com.aya.digital.core.data.profile.mappers.EmergencyContactMapper
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.datasource.DictionariesDataSource
import com.aya.digital.core.datasource.ProfileDataSource
import com.aya.digital.core.ext.*
import com.aya.digital.core.network.model.request.EmergencyContactBody
import com.aya.digital.core.network.model.request.InsuranceBody
import com.aya.digital.core.network.model.request.InsurancePolicyBody
import com.aya.digital.core.network.model.request.ProfileBody
import com.aya.digital.core.network.model.response.profile.InsurancePolicyResponse
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.io.File

internal class ProfileRepositoryImpl(
    private val profileDataSource: ProfileDataSource,
    private val currentProfileMapper: CurrentProfileMapper,
    private val insuranceMapper: InsurancePolicyMapper,
    private val emergencyContactMapper: EmergencyContactMapper,
    private val dictionariesDataSource: DictionariesDataSource,
    private val insuranceCompanyMapper: InsuranceCompanyMapper
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

    override fun getEmergencyContact(): Single<RequestResult<EmergencyContact>> =
        profileDataSource.getEmergencyContact()
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({
                emergencyContactMapper.mapFrom(it).asResult()
            }, { it })

    override fun updateEmergencyContact(body: EmergencyContactBody): Single<RequestResult<Unit>> =
        profileDataSource.updateEmergencyContact(body)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({
                Unit.asResult()
            }, { it })

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

    override fun getInsurances(): Observable<RequestResult<List<InsurancePolicyModel>>> =
        profileDataSource.getInsurances()
            .flatMap { policyResponses ->

                Observable.fromIterable(policyResponses)
                    .flatMap { insurancePolicyResponse ->
                        class DataPair(val name: String, val attachment: Boolean)
                        class PolicyPair(
                            val dataPair: DataPair,
                            val insurancePolicyResponse: InsurancePolicyResponse
                        )
                        Single.zip(
                            profileDataSource.getAttachmentById(insurancePolicyResponse.attachmentId),
                            dictionariesDataSource.getInsuranceCompanyById(insurancePolicyResponse.organizationId)
                        ) { attachment, name ->
                            DataPair(
                                name.first().nameOrg ?: "",
                                true
                            )
                        }
                            .map { PolicyPair(it, insurancePolicyResponse) }
                            .toObservable()
                    }
                    .toList()
                    .toObservable()
            }
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ policyPairs ->
                policyPairs.map { policyPair ->
                    insuranceMapper.mapFrom(policyPair.insurancePolicyResponse).apply {
                        this.organisationName = policyPair.dataPair.name
                        this.attachment = policyPair.dataPair.attachment
                    }
                }.asResult()
            }, { it })

    override fun getInsuranceById(insuranceId: Int): Single<RequestResult<InsurancePolicyModel>> =
        profileDataSource.getInsuranceById(insuranceId)
            .retryOnError()
            .flatMap { insurancePolicyResponse ->
                class DataPair(val name: String, val attachment: Boolean)
                class PolicyPair(
                    val dataPair: DataPair,
                    val insurancePolicyResponse: InsurancePolicyResponse
                )
                Single.zip(
                    profileDataSource.getAttachmentById(insurancePolicyResponse.attachmentId),
                    dictionariesDataSource.getInsuranceCompanyById(insurancePolicyResponse.organizationId)
                ) { attachment, name ->
                    DataPair(
                        name.first().nameOrg ?: "",
                        true
                    )
                }
                    .map { PolicyPair(it, insurancePolicyResponse) }

            }
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ policyPair ->
                val policy = insuranceMapper.mapFrom(policyPair.insurancePolicyResponse)
                policy.attachment = policyPair.dataPair.attachment
                policy.organisationName = policyPair.dataPair.name
                policy.asResult()
            }, { it })
    /*.retrofitResponseToResult(CommonUtils::mapServerErrors)
    .mapResult({insuranceMapper.mapFrom(it).asResult()
    }, { it })
    .flatMapResult({insurancePolicyModel->
                   getAttachmentById(insurancePolicyModel.photoAttachment)

    },{Single.just(it)})*/

    override fun deleteInsurance(insuranceId: Int): Single<RequestResult<Boolean>> =
        profileDataSource.deleteInsurance(insuranceId)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({
                true.asResult()
            }, { it })

    override fun getAttachmentById(attachmentId: Int): Single<RequestResult<Boolean>> =
        profileDataSource.getAttachmentById(attachmentId)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({
                true.asResult()
            }, { it })

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun updatePhoneNumber(number: String): Single<RequestResult<Unit>> {
        TODO("Not yet implemented")
    }

}