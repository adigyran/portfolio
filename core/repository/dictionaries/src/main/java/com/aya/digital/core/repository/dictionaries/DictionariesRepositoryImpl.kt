package com.aya.digital.core.repository.auth

import com.aya.digital.core.data.base.dataprocessing.PaginationPageModel
import com.aya.digital.core.data.profile.InsuranceModel
import com.aya.digital.core.data.profile.mappers.InsuranceMapper
import com.aya.digital.core.data.profile.repository.DictionariesRepository
import com.aya.digital.core.datasource.DictionariesDataSource
import com.aya.digital.core.ext.*
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class DictionariesRepositoryImpl(
    private val dictionariesDataSource: DictionariesDataSource,
    private val insuranceMapper: InsuranceMapper
) : DictionariesRepository {

    override fun getInsurances(searchTerm: String) =
        dictionariesDataSource.getInsuranceCompanies(searchTerm)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                val insurances = insuranceMapper.mapFromList(result.content)
                PaginationPageModel(
                    insurances,
                    result.offset,
                    result.pageNumber,
                    result.numberOfElements,
                    result.totalPages,
                    result.totalElements
                ).asResult()
            }, { it })

    override fun getInsuranceById(id: Int): Single<RequestResult<InsuranceModel>> =
        dictionariesDataSource.getInsuranceCompanyById(id)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                insuranceMapper.mapFrom(result.first()).asResult()
            }, { it })

    override fun getInsurancesByIds(ids: List<Int>): Observable<RequestResult<Set<InsuranceModel>>> =
        dictionariesDataSource.getInsuranceCompaniesByIds(ids)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                insuranceMapper.mapFromList(result).toSet().asResult()
            }, { it })

    /* override fun getInsurancesByIds(ids: List<Int>): Observable<RequestResult<Set<InsuranceModel>>> {
         return Observable.fromIterable(ids)
             .flatMap { getInsuranceById(it).toObservable() }
             .flatMapResult({
                 Observable.just(it)
                     .collectInto(mutableSetOf<InsuranceModel>(), { l, i -> l.add(i) })
                     .toObservable()
                     .map { it.asResult() }
             }, {
                 Observable.just(it)
             })

     }*/


}