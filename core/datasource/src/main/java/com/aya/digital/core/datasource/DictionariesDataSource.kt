package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.response.base.PagedCursorResponse
import com.aya.digital.core.network.model.response.profile.InsuranceCompanyResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface DictionariesDataSource {

    fun getInsuranceCompanies(searchTerm:String) : Flowable<PagedCursorResponse<InsuranceCompanyResponse>>

    fun getInsuranceCompanyById(id:Int) : Single<List<InsuranceCompanyResponse>>

    fun getInsuranceCompaniesByIds(ids:List<Int>) : Observable<List<InsuranceCompanyResponse>>
}