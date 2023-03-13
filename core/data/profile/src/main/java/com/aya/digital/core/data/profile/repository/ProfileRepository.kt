package com.aya.digital.core.data.profile.repository

import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.data.profile.EmergencyContact
import com.aya.digital.core.data.profile.ImageUploadResult
import com.aya.digital.core.data.profile.InsurancePolicyModel
import com.aya.digital.core.network.model.request.EmergencyContactBody
import com.aya.digital.core.network.model.request.InsuranceBody
import com.aya.digital.core.network.model.request.InsurancePolicyBody
import com.aya.digital.core.network.model.request.ProfileBody
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.io.File

interface ProfileRepository {

    fun currentProfileId(): Single<RequestResult<Int>>

    fun currentProfile(): Single<RequestResult<CurrentProfile>>

    fun updateProfile(body: ProfileBody): Single<RequestResult<CurrentProfile>>

    fun getEmergencyContact(): Single<RequestResult<EmergencyContact>>

    fun updateEmergencyContact(body: EmergencyContactBody): Single<RequestResult<Unit>>

    fun uploadImage(file: File): RequestResult<ImageUploadResult>

    fun updatePhoneNumber(number: String): Single<RequestResult<Unit>>

    fun addInsurance(insurancePolicyBody: InsurancePolicyBody): Single<RequestResult<Boolean>>

    fun saveInsurance(
        insuranceId: Int,
        insurancePolicyBody: InsurancePolicyBody
    ): Single<RequestResult<Boolean>>

    fun getInsurances(): Observable<RequestResult<Boolean>>

    fun getInsuranceById(insuranceId: Int): Single<RequestResult<InsurancePolicyModel>>

    fun deleteInsurance(insuranceId: Int): Single<RequestResult<Boolean>>

    fun clear()


}