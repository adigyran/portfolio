package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.EmergencyContactResponse
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

    @GET("api/profile/phone")
    fun getPhoneNumber(): Single<PhoneResponse>

    @PATCH("api/profile/phone")
    fun updatePhoneNumber(@Body body:UpdatePhoneBody):Single<PhoneResponse>

    @POST("api/profile/phone-send-verify-code-sms")
    fun getPhoneVerifyCode():Single<Unit>

    @GET("api/profile/phone-status")
    fun getPhoneVerifiedStatus():Single<Boolean>

    @POST("api/profile/phone-verify")
    fun sendPhoneVerifyCode(@Query("code") code:String):Single<Boolean>

    @POST("api/profile/insurances")
    fun addInsurance(@Body insurancePolicyBody: InsurancePolicyBody):Single<Unit>

    @PATCH("api/profile/insurances/{id}")
    fun saveInsurance(@Path("id") insuranceId:Int, @Body insurancePolicyBody: InsurancePolicyBody) : Single<Unit>

    @GET("api/profile/insurances")
    fun getInsurances():Observable<List<InsurancePolicyResponse>>

    @GET("api/profile/insurances/{id}")
    fun getInsuranceById(@Path("id") insuranceId: Int):Single<InsurancePolicyResponse>

    @GET("api/account/patient/address")
    fun getPatientAddress(): Single<AddressResponse>

    @PATCH("api/account/patient/address")
    fun updatePatientAddress(
        @Body body: PatientProfileBody
    ): Completable

    @GET("api/profile/emergency")
    fun getEmergencyContact(): Single<EmergencyContactResponse>

    @PATCH("api/profile/emergency")
    fun updateEmergencyContact(
        @Body body: EmergencyContactBody
    ): Single<Unit>

    @DELETE("api/profile/insurances/{id}")
    fun deleteInsurance(@Path("id") insuranceId: Int): Single<Unit>

    @GET("api/attachment/{id}")
    fun getAttachmentById(@Path("id") attachmentId: Int): Single<Unit>

    @GET("api/settings/notifications")
    fun getNotificationsSettings():Single<NotificationSettingsResponse>
    @PATCH("api/settings/notifications")
    fun updateNotificationsSettings(@Body body:NotificationSettingsBody):Single<NotificationSettingsResponse>

    @GET("/api/settings/insurances/profile-insurances")
    fun getDoctorInsurancePolicies():Observable<Set<InsuranceCompanyResponse>>

    @POST("api/settings/insurances")
    fun addDoctorInsurancePolicies(@Body ids:Set<Int>):Single<Unit>
    @HTTP(method = "DELETE", path = "api/settings/insurances", hasBody = true)
    fun deleteDoctorInsurancePolicies(@Body ids:Set<Int>):Single<Unit>
}