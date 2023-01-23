package com.aya.digital.core.data.repositories.profile

import com.aya.digital.core.data.model.profile.Address
import com.aya.digital.core.data.model.profile.patient.PatientProfile
import com.aya.digital.core.network.model.request.PatientProfileBody
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single

interface PatientRepository {

    fun getPatient(): Single<RequestResult<PatientProfile>>

    fun updatePatient(body: PatientProfileBody): Single<RequestResult<Unit>>

    fun getPatientAddress(): Single<RequestResult<Address>>

    fun updatePatientAddress(body: PatientProfileBody): Single<RequestResult<Unit>>
}