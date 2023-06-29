package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.base.PagedCursorResponse
import com.aya.digital.core.network.model.response.doctors.CityResponse
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse
import com.aya.digital.core.network.model.response.profile.InsuranceCompanyResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface DictionariesService {

    @GET("search-app/insurances")
    fun getInsurances(@Query("search") searchTerm: String?): Flowable<PagedCursorResponse<InsuranceCompanyResponse>>
    @GET("search-app/insurances")
    fun getInsuranceById(@Query("ids") id: Int): Single<PagedCursorResponse<InsuranceCompanyResponse>>
    @GET("search-app/insurances")
    fun getInsurancesByIds(@Query("ids") ids: List<Int>): Observable<PagedCursorResponse<InsuranceCompanyResponse>>

    @GET("search-app/specialities")
    fun getSpecialities(@Query("search") searchTerm: String?): Flowable<PagedCursorResponse<SpecialityResponse>>

    @GET("api/cities")
    fun getCities(@Query("search") searchTerm: String?): Flowable<CityResponse>

}