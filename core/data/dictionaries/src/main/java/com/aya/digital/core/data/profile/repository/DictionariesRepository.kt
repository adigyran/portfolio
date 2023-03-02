package com.aya.digital.core.data.profile.repository

import com.aya.digital.core.data.base.dataprocessing.PaginationPageModel
import com.aya.digital.core.data.profile.InsuranceModel
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface DictionariesRepository {
    fun getInsurances(searchTerm:String): Flowable<RequestResult<PaginationPageModel<InsuranceModel>>>

    fun getInsuranceById(id:Int):Single<RequestResult<InsuranceModel>>

    fun getInsurancesByIds(ids:List<Int>) : Observable<RequestResult<Set<InsuranceModel>>>
}