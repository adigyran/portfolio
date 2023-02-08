package com.aya.digital.core.data.profile.repository

import com.aya.digital.core.data.profile.Address
import com.aya.digital.core.data.profile.patient.PatientProfile
import com.aya.digital.core.network.model.request.PatientProfileBody
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single

interface PatientRepository {

    fun getPatient(): Single<RequestResult<PatientProfile>>

    fun updatePatient(body: com.aya.digital.core.network.model.request.PatientProfileBody): Single<RequestResult<Unit>>

    fun getPatientAddress(): Single<RequestResult<Address>>

    fun updatePatientAddress(body: com.aya.digital.core.network.model.request.PatientProfileBody): Single<RequestResult<Unit>>
}