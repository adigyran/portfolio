package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.EmergencyContactResponse
import com.aya.digital.core.network.model.response.language.LanguageResponse
import com.aya.digital.core.network.model.response.profile.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.RequestBody
import retrofit2.http.*

interface PractitionerProfileService {

    @GET("/api/settings/insurances/profile-insurances")
    fun getDoctorInsurancePolicies():Observable<Set<InsuranceCompanyResponse>>

    @POST("api/settings/insurances")
    fun addDoctorInsurancePolicies(@Body ids:Set<Int>):Completable
    @HTTP(method = "DELETE", path = "api/settings/insurances", hasBody = true)
    fun deleteDoctorInsurancePolicies(@Body ids:Set<Int>):Completable

    @GET("/api/profile/about-info")
    fun getDoctorBio():Single<BioResponse>

    @PATCH("api/profile/about-info")
    fun updateDoctorBio(@Body bioBody: BioBody):Completable

    @GET("/api/settings/languages")
    fun getDoctorLanguages():Observable<Set<LanguageResponse>>

    @POST("api/settings/languages")
    fun addDoctorLanguages(@Body languages:Set<Int>):Completable

    @HTTP(method = "DELETE", path = "api/settings/languages", hasBody = true)
    fun removeDoctorLanguages(@Body languages: Set<Int>):Completable
}