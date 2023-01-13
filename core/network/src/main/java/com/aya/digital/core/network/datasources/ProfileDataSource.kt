package com.aya.digital.core.network.datasources

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.EmergencyContactResponse
import com.aya.digital.core.network.model.response.Message
import com.aya.digital.core.network.model.response.doctor.PractitionerProfileResponse
import com.aya.digital.core.network.model.response.patient.PatientProfileResponse
import com.aya.digital.core.network.model.response.profile.AddressResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.network.model.response.profile.ImageUploadResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody

interface ProfileDataSource {


    fun currentProfile(): Single<CurrentProfileResponse>

    fun updateProfile(
        body: ProfileBody
    ): Completable

    fun currentPatient(): Single<PatientProfileResponse>

    fun updatePatient(
        body: PatientProfileBody
    ): Completable

    fun getPatientAddress(): Single<AddressResponse>

    fun updatePatientAddress(
        body: PatientProfileBody
    ): Completable


    fun getEmergencyContact(): Single<EmergencyContactResponse>

    fun updateEmergencyContact(
        body: EmergencyContactBody
    ): Completable


    fun currentPractitioner(): Single<PractitionerProfileResponse>


    fun getPractitionerPhoneNumber(): Single<PractitionerProfileResponse>


    fun updatePractitioner(
        body: PractitionerProfileBody
    ): Completable

    fun updatePractitionerPhoneNumber(
        body: PractitionerProfileBody
    ): Completable


    fun updatePractitionerAddress(
        body: PractitionerProfileBody
    ): Completable

    fun getPractitionerAddress(): Single<AddressResponse>

    fun uploadAvatar(
        file: MultipartBody.Part
    ): Single<ImageUploadResponse>

    fun deleteAvatar(): Completable

    fun registration(body: RegistrationBody): Single<Message>

    fun logout(
        clientId: String,
        refreshToken: String,
    ): Completable
}