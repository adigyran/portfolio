package com.aya.digital.core.repository.profile.practitioner

import android.content.Context
import com.aya.digital.core.data.dictionaries.LanguageModel
import com.aya.digital.core.data.dictionaries.mappers.LanguageMapper
import com.aya.digital.core.data.profile.DoctorBio
import com.aya.digital.core.data.profile.mappers.DoctorBioMapper
import com.aya.digital.core.data.profile.repository.PractitionerRepository
import com.aya.digital.core.datasource.ProfilePractitionerDataSource
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.ext.retrofitResponseToResult
import com.aya.digital.core.ext.retryOnError
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

internal class PractitionerRepositoryImpl(
    private val context: Context,
    private val practitionerDataSource: ProfilePractitionerDataSource,
    private val languageMapper: LanguageMapper,
    private val bioMapper: DoctorBioMapper
) : PractitionerRepository {
    override fun getDoctorBio(): Single<RequestResult<DoctorBio>> =
        practitionerDataSource
            .getDoctorBio()
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                bioMapper.mapFrom(result).asResult()
            }, { it })

    override fun saveDoctorBio(bioText: String): Single<RequestResult<Boolean>> =
        practitionerDataSource
            .updateDoctorBio(bioText)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                true.asResult()
            }, { it })

    override fun getDoctorLanguages(): Observable<RequestResult<Set<LanguageModel>>> =
        practitionerDataSource
            .getDoctorLanguages()
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                result.map { languageMapper.mapFrom(it) }.toSet().asResult()
            }, { it })

    override fun addDoctorLanguages(languages: Set<Int>): Single<RequestResult<Boolean>> =
        practitionerDataSource
            .addDoctorLanguages(languages)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                true.asResult()
            }, { it })

    override fun removeDoctorLanguages(languages: Set<Int>): Single<RequestResult<Boolean>> =
        practitionerDataSource
            .removeDoctorLanguages(languages)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                true.asResult()
            }, { it })
}