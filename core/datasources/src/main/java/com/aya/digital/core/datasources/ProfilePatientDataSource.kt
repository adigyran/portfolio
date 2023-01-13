package com.aya.digital.core.datasources

import com.aya.digital.core.network.model.request.PatientProfileBody
import com.aya.digital.core.network.model.response.patient.PatientProfileResponse
import com.aya.digital.core.network.model.response.profile.AddressResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ProfilePatientDataSource {

    fun currentPatient(): Single<PatientProfileResponse>

    fun updatePatient(
        body: PatientProfileBody
    ): Completable

    fun getPatientAddress(): Single<AddressResponse>

    fun updatePatientAddress(
        body: PatientProfileBody
    ): Completable
}