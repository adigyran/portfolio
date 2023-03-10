package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.EmergencyContactResponse
import com.aya.digital.core.network.model.response.profile.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface ProfileService {

    @GET("profile")
    fun currentProfile(): Single<CurrentProfileResponse>

    @PATCH("profile")
    fun updateProfile(
        @Body body: ProfileBody
    ): Single<CurrentProfileResponse>

    @Multipart
    @POST("attachment")
    fun uploadImage(@Part image:MultipartBody.Part) : Single<ImageUploadResponse>

    @POST("profile/insurances")
    fun addInsurance(@Body insurancePolicyBody: InsurancePolicyBody):Single<Unit>

    @PATCH("profile/insurances/{id}")
    fun saveInsurance(@Path("id") insuranceId:Int, @Body insurancePolicyBody: InsurancePolicyBody) : Single<Unit>

    @GET("profile/insurances")
    fun getInsurances():Observable<Unit>

    @GET("profile/insurances/{id}")
    fun getInsuranceById(@Path("id") insuranceId: Int):Single<InsurancePolicyResponse>

    @GET("account/patient/address")
    fun getPatientAddress(): Single<AddressResponse>

    @PATCH("account/patient/address")
    fun updatePatientAddress(
        @Body body: PatientProfileBody
    ): Completable

    @GET("account/patient/contact")
    fun getEmergencyContact(): Single<EmergencyContactResponse>

    @PATCH("account/patient/contact")
    fun updateEmergencyContact(
        @Body body: EmergencyContactBody
    ): Completable

}