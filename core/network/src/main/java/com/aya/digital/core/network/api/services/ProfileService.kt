package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.EmergencyContactResponse
import com.aya.digital.core.network.model.response.Message
import com.aya.digital.core.network.model.response.doctor.PractitionerProfileResponse
import com.aya.digital.core.network.model.response.patient.PatientProfileResponse
import com.aya.digital.core.network.model.response.profile.AddressResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.network.model.response.profile.ImageUploadResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ProfileService {

    @GET("account/profile")
    fun currentProfile(): Single<CurrentProfileResponse>

    @PUT("account/profile")
    fun updateProfile(
        @Body body: ProfileBody
    ): Completable

    @GET("account/patient")
    fun currentPatient(): Single<PatientProfileResponse>

    @PATCH("account/patient")
    fun updatePatient(
        @Body body: PatientProfileBody
    ): Completable

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

    @GET("account/practitioner")
    fun currentPractitioner(): Single<PractitionerProfileResponse>

    @GET("account/practitioner/phone-number")
    fun getPractitionerPhoneNumber(): Single<PractitionerProfileResponse>

    @PATCH("account/practitioner/profile-data")
    fun updatePractitioner(
        @Body body: PractitionerProfileBody
    ): Completable

    @PATCH("account/practitioner/phone-number")
    fun updatePractitionerPhoneNumber(
        @Body body: PractitionerProfileBody
    ): Completable

    @PATCH("account/practitioner/address")
    fun updatePractitionerAddress(
        @Body body: PractitionerProfileBody
    ): Completable

    @GET("account/practitioner/address")
    fun getPractitionerAddress(): Single<AddressResponse>

    @Multipart
    @POST("account/upload")
    fun uploadAvatar(
        @Part file: MultipartBody.Part
    ): Single<ImageUploadResponse>

    @DELETE("account/profile/avatar")
    fun deleteAvatar(): Completable

    @POST("auth/registration")
    fun registration(@Body body: RegistrationBody): Single<Message>

    @FormUrlEncoded
    @POST("https://auth.aya-doc.com/auth/realms/aya-realm/protocol/openid-connect/logout")
    fun logout(
        @Field("client_id") clientId: String,
        @Field("refresh_token") refreshToken: String,
    ): Completable
}