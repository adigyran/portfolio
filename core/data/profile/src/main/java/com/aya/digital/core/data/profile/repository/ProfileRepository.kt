package com.aya.digital.core.data.profile.repository

import android.net.Uri
import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.data.profile.EmergencyContact
import com.aya.digital.core.data.profile.ImageUploadResult
import com.aya.digital.core.data.profile.InsurancePolicyModel
import com.aya.digital.core.network.model.request.EmergencyContactBody
import com.aya.digital.core.network.model.request.InsurancePolicyBody
import com.aya.digital.core.network.model.request.ProfileBody
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ProfileRepository {

    fun currentProfileId(): Single<RequestResult<Int>>

    fun currentProfile(): Single<RequestResult<CurrentProfile>>

    fun updateProfile(body: ProfileBody): Single<RequestResult<CurrentProfile>>

    fun getEmergencyContact(): Single<RequestResult<EmergencyContact>>

    fun updateEmergencyContact(body: EmergencyContactBody): Single<RequestResult<Unit>>


    fun uploadAttachment(uri: Uri): Single<RequestResult<ImageUploadResult>>

    fun uploadAvatar(uri: Uri): Single<RequestResult<Boolean>>

    fun updatePhoneNumber(number: String): Single<RequestResult<Unit>>

    fun addInsurance(insurancePolicyBody: InsurancePolicyBody): Single<RequestResult<Boolean>>

    fun saveInsurance(
        insuranceId: Int,
        insurancePolicyBody: InsurancePolicyBody
    ): Single<RequestResult<Boolean>>

    fun getInsurances(): Observable<RequestResult<List<InsurancePolicyModel>>>

    fun getInsuranceById(insuranceId: Int): Single<RequestResult<InsurancePolicyModel>>

    fun deleteInsurance(insuranceId: Int): Single<RequestResult<Boolean>>

    fun getAttachmentById(attachmentId: Int): Single<RequestResult<Boolean>>

    fun clear()


}