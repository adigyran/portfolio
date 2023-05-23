package com.aya.digital.core.repository.dictionaries

import com.aya.digital.core.data.base.dataprocessing.PaginationCursorModel
import com.aya.digital.core.data.dictionaries.InsuranceCompanyModel
import com.aya.digital.core.data.dictionaries.mappers.InsuranceCompanyMapper
import com.aya.digital.core.data.dictionaries.repository.DictionariesRepository
import com.aya.digital.core.datasource.DictionariesDataSource
import com.aya.digital.core.ext.*
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class DictionariesRepositoryImpl(
    private val dictionariesDataSource: DictionariesDataSource,
    private val insuranceMapper: InsuranceCompanyMapper
) : DictionariesRepository {

    override fun getInsuranceCompanies(searchTerm: String?) =
        dictionariesDataSource.getInsuranceCompanies(searchTerm)
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
}