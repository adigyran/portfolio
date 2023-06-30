package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.PractitionerFavoriteBody
import com.aya.digital.core.network.model.response.base.PagedCursorResponse
import com.aya.digital.core.network.model.response.doctors.DoctorDataResponse
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse
import com.aya.digital.core.network.model.response.patient.PatientDataResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PractitionersService {

    //limit=10&sortDirection=ASC
    @GET("search-app/practitioners")
    fun fetchPractitioners(
        @Query("scrollId") scrollId: String?,
        @Query("specCodes") specialisations: List<Int>?,
        @Query("insIds") insurances: List<Int>?,
        @Query("city") city: String?
    ): Flowable<PagedCursorResponse<DoctorDataResponse>>

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

    @GET("api/patients/{id}")
    fun getPatient(@Path("id") id: Int): Single<PatientDataResponse>

    @POST("api/profile/favorites")
    fun addPractitionerToFavorites(@Body body: PractitionerFavoriteBody): Single<Boolean>

    @DELETE("api/profile/favorites/{id}")
    fun removePractitionerFromFavorites(@Path("id") id: Int): Single<Boolean>

    @GET("api/profile/favorites")
    fun getFavoritePractitioners(): Flowable<List<DoctorDataResponse>>

}