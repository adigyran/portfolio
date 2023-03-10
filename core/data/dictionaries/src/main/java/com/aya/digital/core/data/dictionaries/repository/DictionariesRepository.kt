package com.aya.digital.core.data.dictionaries.repository

import com.aya.digital.core.data.base.dataprocessing.PaginationPageModel
import com.aya.digital.core.data.dictionaries.InsuranceCompanyModel
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface DictionariesRepository {
    fun getInsuranceCompanies(searchTerm:String): Flowable<RequestResult<PaginationPageModel<InsuranceCompanyModel>>>

    fun getInsuranceCompanyById(id:Int):Single<RequestResult<InsuranceCompanyModel>>

    fun getInsuranceCompaniesByIds(ids:List<Int>) : Observable<RequestResult<Set<InsuranceCompanyModel>>>
}