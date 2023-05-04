package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.response.base.PagedCursorResponse
import com.aya.digital.core.network.model.response.doctors.DoctorDataResponse
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PractitionersService {

    //limit=10&sortDirection=ASC
    @GET("search-app/practitioners")
    fun fetchPractitioners(@Query("scrollId") scrollId: String?): Flowable<PagedCursorResponse<DoctorDataResponse>>

    @GET("search-app/practitioners/{id}")
    fun fetchPractitionerById(
        @Path("id") id: Int,
    ): Single<DoctorDataResponse>


    @GET("specialities")
    fun fetchSpecialities(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("sortingFields") sortingFields: List<String>?,
        @Query("sortDirection") sortDirection: String?,
        @Query("specialtyName") name: String?,
    ): Flowable<PagedCursorResponse<SpecialityResponse>>

    @GET("specialities/{id}")
    fun fetchSpeciality(
        @Path("id") id: Int,
    ): Single<SpecialityResponse>
}