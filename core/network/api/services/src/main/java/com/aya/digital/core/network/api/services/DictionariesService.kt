package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.auth.LoginResponse
import com.aya.digital.core.network.model.response.base.PagedResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.network.model.response.profile.InsuranceCompanyResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface DictionariesService {

    @GET("insurances")
    fun getInsurances(@Query("search") searchTerm: String): Flowable<PagedResponse<InsuranceCompanyResponse>>
    @GET("insurances/select")
    fun getInsuranceById(@Query("ids") id: Int): Single<List<InsuranceCompanyResponse>>
    @GET("insurances/select")
    fun getInsurancesByIds(@Query("ids") ids: List<Int>): Observable<List<InsuranceCompanyResponse>>

}