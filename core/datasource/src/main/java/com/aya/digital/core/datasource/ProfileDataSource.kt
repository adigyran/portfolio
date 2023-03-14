package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.request.EmergencyContactBody
import com.aya.digital.core.network.model.request.InsuranceBody
import com.aya.digital.core.network.model.request.InsurancePolicyBody
import com.aya.digital.core.network.model.request.ProfileBody
import com.aya.digital.core.network.model.response.EmergencyContactResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.network.model.response.profile.ImageUploadResponse
import com.aya.digital.core.network.model.response.profile.InsuranceCompanyResponse
import com.aya.digital.core.network.model.response.profile.InsurancePolicyResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody

interface ProfileDataSource {


    fun currentProfile(): Single<CurrentProfileResponse>

    fun updateProfile(
        body: ProfileBody
    ): Single<CurrentProfileResponse>

    fun getEmergencyContact(): Single<EmergencyContactResponse>

    fun updateEmergencyContact(
        body: EmergencyContactBody
    ): Single<Unit>

    fun uploadImage(
        file: MultipartBody.Part
    ): Single<ImageUploadResponse>

    fun addInsurance(insurancePolicyBody: InsurancePolicyBody):Single<Unit>

    fun saveInsurance(insuranceId:Int, insurancePolicyBody: InsurancePolicyBody) : Single<Unit>

    fun getInsurances(): Observable<List<InsurancePolicyResponse>>

    fun getInsuranceById(insuranceId: Int):Single<InsurancePolicyResponse>

    fun deleteInsurance(insuranceId: Int):Single<Unit>

    fun getAttachmentById(attachmentId:Int):Single<Unit>

}