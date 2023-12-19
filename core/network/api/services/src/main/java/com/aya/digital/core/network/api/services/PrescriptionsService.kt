package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.GetTelehealthRoomTokenBody
import com.aya.digital.core.network.model.request.PrescriptionContentBody
import com.aya.digital.core.network.model.request.PrescriptionSubscribeBody
import com.aya.digital.core.network.model.response.telehealth.TelehealthRoomTokenResponse
import com.aya.digital.core.network.model.response.telehealth.TelehealthWaitTimeResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface PrescriptionsService {

    @PATCH("settings/prescriptions/{id}")
    fun editPrescriptionByPrescriptionId(@Path("id") prescriptionId: Int, @Body prescriptionContentBody: PrescriptionContentBody): Single<Boolean>

    @PATCH("settings/prescriptions/update-content-by-appId/{id}")
    fun editPrescriptionByAppointmentId(@Path("id") appointmentId: Int, @Body prescriptionContentBody: PrescriptionContentBody): Single<Boolean>

    @PUT("settings/prescriptions/subscribe")
    fun subscribeToPrescriptions(@Body prescriptionSubscribeBody: PrescriptionSubscribeBody): Completable

    @GET("settings/prescriptions/get-content-by-appId/{id}")
    fun getPrescriptionsByAppointmentId(@Path("id") appointmentId: Int): Single<Boolean>

    @GET("settings/prescriptions/{id}")
    fun getPrescriptionById(@Path("id") prescriptionId: Int): Single<Boolean>

    @GET("settings/prescriptions/patient-prescription-list/{id}")
    fun getPatientPrescriptionsByPatientId(@Path("id") patientId: Int): Single<Boolean>

    @GET("settings/prescriptions/practitioner-prescription-list/{id}")
    fun getDoctorPrescriptionsByPatientId(@Path("id") patientId: Int): Single<Boolean>
}