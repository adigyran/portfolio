package com.aya.digital.core.repository.dictionaries

import com.aya.digital.core.data.base.dataprocessing.PaginationCursorModel
import com.aya.digital.core.data.dictionaries.CityModel
import com.aya.digital.core.data.dictionaries.InsuranceCompanyModel
import com.aya.digital.core.data.dictionaries.LanguageModel
import com.aya.digital.core.data.dictionaries.MedicalDegreeModel
import com.aya.digital.core.data.dictionaries.SpecialityModel
import com.aya.digital.core.data.dictionaries.mappers.CityMapper
import com.aya.digital.core.data.dictionaries.mappers.InsuranceCompanyMapper
import com.aya.digital.core.data.dictionaries.mappers.LanguageMapper
import com.aya.digital.core.data.dictionaries.mappers.MedicalDegreeMapper
import com.aya.digital.core.data.dictionaries.mappers.SpecialityMapper
import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.datasource.DictionariesDataSource
import com.aya.digital.core.ext.*
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class DictionariesRepositoryImpl(
    private val dictionariesDataSource: DictionariesDataSource,
    private val insuranceMapper: InsuranceCompanyMapper,
    private val specialityMapper: SpecialityMapper,
    private val cityMapper: CityMapper,
    private val languageMapper: LanguageMapper,
    private val medicalDegreeMapper: MedicalDegreeMapper
) : DictionariesRepository {

    override fun getInsuranceCompanies(
        searchTerm: String?,
        selectedIds: List<Int>,
        cursor: String?
    ) =
        dictionariesDataSource.getInsuranceCompanies(searchTerm, selectedIds, cursor)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                val insurances = insuranceMapper.mapFromList(result.data)
                PaginationCursorModel(
                    insurances,
                    result.scrollToken,
                    result.sizeResult
                ).asResult()
            }, { it })

    override fun getInsuranceCompanyById(id: Int): Single<RequestResult<InsuranceCompanyModel>> =
        dictionariesDataSource.getInsuranceCompanyById(id)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                val insurances = insuranceMapper.mapFromList(result.data)
                insurances.first().asResult()
            }, { it })

    override fun getInsuranceCompaniesByIds(ids: List<Int>): Observable<RequestResult<Set<InsuranceCompanyModel>>> =
        dictionariesDataSource.getInsuranceCompaniesByIds(ids)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                val insurances = insuranceMapper.mapFromList(result.data)
                insurances.toSet().asResult()
            }, { it })

    override fun getSpecialities(
        searchTerm: String?,
        selectedIds: List<Int>,
        cursor: String?
    ): Flowable<RequestResult<PaginationCursorModel<SpecialityModel>>> =
        dictionariesDataSource.getSpecialisations(searchTerm, selectedIds, cursor)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                val specialisations = specialityMapper.mapFromList(result.data)
                PaginationCursorModel(
                    specialisations,
                    result.scrollToken,
                    result.sizeResult
                ).asResult()
            }, { it })

    override fun getCities(
        searchTerm: String?,
        selectedIds: List<Int>,
        cursor: String?
    ): Flowable<RequestResult<PaginationCursorModel<CityModel>>> =
        dictionariesDataSource.getCities(searchTerm, selectedIds, cursor)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult(
                { result ->
                    val cities = cityMapper.mapFromList(result.data)
                    PaginationCursorModel(
                        cities,
                        result.scrollToken,
                        result.sizeResult
                    ).asResult()
                },
                { it }
            )

    override fun getLanguages(
        searchTerm: String?,
        selectedIds: List<Int>,
        cursor: String?
    ): Flowable<RequestResult<PaginationCursorModel<LanguageModel>>> =
        dictionariesDataSource.getLanguages(searchTerm, selectedIds, cursor)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult(
                { result ->
                    val languages = languageMapper.mapFromList(result.data)
                    PaginationCursorModel(
                        languages,
                        result.scrollToken,
                        result.sizeResult
                    ).asResult()
                },
                { it }
            )

    override fun getMedicalDegrees(
        searchTerm: String?,
        selectedIds: List<Int>,
        cursor: String?
    ): Flowable<RequestResult<PaginationCursorModel<MedicalDegreeModel>>> =
        dictionariesDataSource.getMedicalDegrees(searchTerm, selectedIds, cursor)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult(
                { result ->
                    val medicalDegreeModels = medicalDegreeMapper.mapFromList(result.data)
                    PaginationCursorModel(
                        medicalDegreeModels,
                        result.scrollToken,
                        result.sizeResult
                    ).asResult()
                },
                { it }
            )
}