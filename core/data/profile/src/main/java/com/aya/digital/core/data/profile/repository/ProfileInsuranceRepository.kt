package com.aya.digital.core.data.profile.repository

import android.net.Uri
import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.data.profile.EmergencyContact
import com.aya.digital.core.data.profile.ImageUploadResult
import com.aya.digital.core.data.profile.InsurancePolicyModel
import com.aya.digital.core.data.profile.NotificationsStatus
import com.aya.digital.core.network.model.request.EmergencyContactBody
import com.aya.digital.core.network.model.request.InsurancePolicyBody
import com.aya.digital.core.network.model.request.ProfileBody
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ProfileInsuranceRepository {

    fun addInsurance(insurancePolicyBody: InsurancePolicyBody): Single<RequestResult<Boolean>>

    fun saveInsurance(
        insuranceId: Int,
        insurancePolicyBody: InsurancePolicyBody
    ): Single<RequestResult<Boolean>>

    fun getInsurances(): Observable<RequestResult<List<InsurancePolicyModel>>>

    fun getInsuranceById(insuranceId: Int): Single<RequestResult<InsurancePolicyModel>>

    fun deleteInsurance(insuranceId: Int): Single<RequestResult<Boolean>>

}