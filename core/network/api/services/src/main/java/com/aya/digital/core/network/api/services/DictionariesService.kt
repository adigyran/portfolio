package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.base.PagedCursorResponse
import com.aya.digital.core.network.model.response.doctors.CityResponse
import com.aya.digital.core.network.model.response.doctors.MedicalDegreeResponse
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse
import com.aya.digital.core.network.model.response.emergencycontact.EmergencyContactTypeResponse
import com.aya.digital.core.network.model.response.language.LanguageResponse
import com.aya.digital.core.network.model.response.profile.InsuranceCompanyResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface DictionariesService {

    @GET("search-app/insurances")
    fun getInsurances(
        @Query("search") searchTerm: String?,
        @Query("scrollId") cursor: String?,
        @Query("selectItemsIds") selectedItems: List<Int>?,
        @Query("sizeCollection") size: Int = 25
    ): Flowable<PagedCursorResponse<InsuranceCompanyResponse>>

    @GET("search-app/insurances")
    fun getInsuranceById(@Query("ids") id: Int): Single<PagedCursorResponse<InsuranceCompanyResponse>>

    @GET("search-app/insurances")
    fun getInsurancesByIds(@Query("ids") ids: List<Int>): Observable<PagedCursorResponse<InsuranceCompanyResponse>>

    @GET("search-app/specialities")
    fun getSpecialities(
        @Query("search") searchTerm: String?,
        @Query("scrollId") cursor: String?,
        @Query("selectItemsIds") selectedItems: List<Int>?,
        @Query("sizeCollection") size: Int = 25
    ): Flowable<PagedCursorResponse<SpecialityResponse>>

    @GET("search-app/api/cities")
    fun getCities(
        @Query("search") searchTerm: String?,
        @Query("scrollId") cursor: String?,
        @Query("selectItemsIds") selectedItems: List<Int>?,
        @Query("sizeCollection") size: Int = 25
    ): Flowable<PagedCursorResponse<CityResponse.CityContent>>

    @GET("search-app/languages")
    fun getLanguages(
        @Query("search") searchTerm: String?,
        @Query("scrollId") cursor: String?,
        @Query("selectItemsIds") selectedItems: List<Int>?,
        @Query("sizeCollection") size: Int = 25
    ): Flowable<PagedCursorResponse<LanguageResponse>>

    @GET("search-app/medical-degrees")
    fun getMedicalDegrees(
        @Query("search") searchTerm: String?,
        @Query("scrollId") cursor: String?,
        @Query("selectItemsIds") selectedItems: List<Int>?,
        @Query("sizeCollection") size: Int = 25
    ): Flowable<PagedCursorResponse<MedicalDegreeResponse>>

    @GET("search-app/api/emergency-types")
    fun getEmergencyContactsTypes(
        @Query("search") searchTerm: String?,
        @Query("scrollId") cursor: String?,
        @Query("selectItemsIds") selectedItems: List<Int>?,
        @Query("sizeCollection") size: Int = 25
    ): Flowable<PagedCursorResponse<EmergencyContactTypeResponse>>

    @GET("search-app/api/emergency-types/{id}")
    fun getEmergencyContactsTypeById(
        @Path("id") id: Int
    ): Single<EmergencyContactTypeResponse>
}