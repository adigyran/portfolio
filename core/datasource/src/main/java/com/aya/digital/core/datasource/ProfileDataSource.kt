package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.request.EmergencyContactBody
import com.aya.digital.core.network.model.request.InsurancePolicyBody
import com.aya.digital.core.network.model.request.NotificationSettingsBody
import com.aya.digital.core.network.model.request.ProfileBody
import com.aya.digital.core.network.model.request.UpdatePhoneBody
import com.aya.digital.core.network.model.response.EmergencyContactResponse
import com.aya.digital.core.network.model.response.profile.AvatarResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.network.model.response.profile.ImageUploadResponse
import com.aya.digital.core.network.model.response.profile.InsurancePolicyResponse
import com.aya.digital.core.network.model.response.profile.NotificationSettingsResponse
import com.aya.digital.core.network.model.response.profile.PhoneResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.RequestBody

interface ProfileDataSource {


    fun currentProfile(): Single<CurrentProfileResponse>

    fun currentAvatar():Single<AvatarResponse>
    fun updateProfile(
        body: ProfileBody
    ): Single<CurrentProfileResponse>

    fun uploadAvatar(
        file: RequestBody
    ): Single<Unit>
    fun getEmergencyContact(): Single<EmergencyContactResponse>

    fun updateEmergencyContact(
        body: EmergencyContactBody
    ): Single<Unit>

    fun uploadAttachmentImage(
        mime:String,
        file: RequestBody
    ): Single<ImageUploadResponse>

    fun getPhoneNumber():Single<PhoneResponse>

    fun updatePhoneNumber(body:UpdatePhoneBody):Single<PhoneResponse>

    fun getPhoneVerificationCode():Single<Unit>

    fun getPhoneVerifiedStatus():Single<Boolean>

    fun sendPhoneVerifyCode(code:String):Single<Unit>
    fun addInsurance(insurancePolicyBody: InsurancePolicyBody):Single<Unit>

    fun saveInsurance(insuranceId:Int, insurancePolicyBody: InsurancePolicyBody) : Single<Unit>

    fun getInsurances(): Observable<List<InsurancePolicyResponse>>

    fun getInsuranceById(insuranceId: Int):Single<InsurancePolicyResponse>

    fun deleteInsurance(insuranceId: Int):Single<Unit>

    fun getAttachmentById(attachmentId:Int):Single<Unit>

    fun getNotificationsSettings():Single<NotificationSettingsResponse>

    fun updateNotificationsSettings(body:NotificationSettingsBody):Single<NotificationSettingsResponse>

}