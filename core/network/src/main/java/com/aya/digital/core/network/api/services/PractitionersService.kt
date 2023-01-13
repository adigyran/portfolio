package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.response.base.PagedResponse
import com.aya.digital.core.network.model.response.doctors.DoctorDataResponse
import com.aya.digital.core.network.model.response.doctors.PractitionersResponse
import com.aya.digital.core.network.model.response.doctors.SpecialitiesSpecialityResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PractitionersService {

    //limit=10&sortDirection=ASC
    @GET("practitioners/")
    fun fetchPractitioners(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("search", encoded = true) search: String?,
        @Query("specialty") specialty: String?,
        @Query("specialtyCode") specialtyCode: String?,
        @Query("sortingFields") sortingFields: List<String>?,
        @Query("sortDirection") sortDirection: String?,
    ): Flowable<PagedResponse<DoctorDataResponse>>

    @GET("practitioners/{id}")
    fun fetchPractitionerById(
        @Path("id") id: Int,
    ): Single<DoctorDataResponse>

    @GET("practitioners/filter-search-full")
    fun searchPractitioners(
        @Query("search") search: String,
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 10
    ): Flowable<PractitionersResponse>

    @GET("practitioners/filter-search-by-speciality-area/{specialityCode}")
    fun searchPractitionersWithCode(
        @Path("specialityCode") code: String,
        @Query("search") search: String,
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 10
    ): Flowable<PractitionersResponse>

    @GET("specialities")
    fun fetchSpecialities(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("sortingFields") sortingFields: List<String>?,
        @Query("sortDirection") sortDirection: String?,
        @Query("specialtyName") name: String?,
    ): Flowable<PagedResponse<SpecialitiesSpecialityResponse>>

    @GET("specialities/{id}")
    fun fetchSpeciality(
        @Path("id") id: Int,
    ): Single<SpecialitiesSpecialityResponse>
}