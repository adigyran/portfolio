package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.emergencycontact.EmergencyContactResponse
import com.aya.digital.core.network.model.response.profile.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.RequestBody
import retrofit2.http.*

interface ProfileService {

    @GET("api/profile")
    fun currentProfile(): Single<CurrentProfileResponse>

    @GET("api/profile/avatar")
    fun getCurrentProfileAvatar():Single<AvatarResponse>
    @PATCH("api/profile")
    fun updateProfile(
        @Body body: ProfileBody
    ): Single<CurrentProfileResponse>

    @POST("api/profile/avatar/upload")
    fun uploadAvatar(@Body image: RequestBody) : Single<Unit>
    @POST("api/attachment")
    fun uploadImage(@Body image: RequestBody) : Single<ImageUploadResponse>

    @GET("api/settings/phone")
    fun getPhoneNumber(): Single<PhoneResponse>

    @PATCH("api/settings/phone")
    fun updatePhoneNumber(@Body body:UpdatePhoneBody):Single<PhoneResponse>

    @POST("api/settings/phone/send-verify-code")
    fun getPhoneVerifyCode():Single<Unit>

    @GET("api/settings/phone/status")
    fun getPhoneVerifiedStatus():Single<Boolean>

    @POST("api/settings/phone/verify")
    fun sendPhoneVerifyCode(@Query("code") code:String):Single<Boolean>

    @POST("api/profile/insurances")
    fun addInsurance(@Body insurancePolicyBody: InsurancePolicyBody):Single<Unit>

    @PATCH("api/profile/insurances/{id}")
    fun saveInsurance(@Path("id") insuranceId:Int, @Body insurancePolicyBody: InsurancePolicyBody) : Single<Unit>

    @GET("api/profile/insurances")
    fun getInsurances():Observable<List<InsurancePolicyResponse>>

    @GET("api/profile/insurances/{id}")
    fun getInsuranceById(@Path("id") insuranceId: Int):Single<InsurancePolicyResponse>


    @GET("api/settings/address")
    fun getAddress(): Single<AddressResponse>

    @PUT("api/settings/address")
    fun updatePatientAddress(
        @Body body: AddressBody
    ): Completable

    @PUT("api/settings/address/refresh-by-coordinates")
    fun updatePatientAddressByCoordinates(
        @Body body: AddressBody
    ): Completable


    @GET("api/profile/emergency")
    fun getEmergencyContact(): Single<EmergencyContactResponse>

    @GET("api/profile/emergency/v2")
    fun getEmergencyContacts(): Single<List<EmergencyContactResponse>>

    @POST("api/profile/emergency/v2")
    fun createEmergencyContact(@Body body: EmergencyContactBody): Completable

    @POST("api/profile/emergency/v2/{id}")
    fun updateEmergencyContact(@Path("id") contactId:Int, @Body body: EmergencyContactBody): Completable

    @DELETE("api/profile/emergency/v2/{id}")
    fun deleteEmergencyContact(@Path("id") contactId:Int): Completable

  /*  @PATCH("api/profile/emergency")
    fun updateEmergencyContact(
        @Body body: EmergencyContactBody
    ): Single<Unit>*/

    @DELETE("api/profile/insurances/{id}")
    fun deleteInsurance(@Path("id") insuranceId: Int): Single<Unit>

    @GET("api/attachment/{id}")
    fun getAttachmentById(@Path("id") attachmentId: Int): Single<Unit>

    @GET("api/settings/notifications")
    fun getNotificationsSettings():Single<NotificationSettingsResponse>
    @PATCH("api/settings/notifications")
    fun updateNotificationsSettings(@Body body:NotificationSettingsBody):Single<NotificationSettingsResponse>
}