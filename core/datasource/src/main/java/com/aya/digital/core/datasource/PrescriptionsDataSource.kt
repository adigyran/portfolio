package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.request.GetTelehealthRoomTokenBody
import com.aya.digital.core.network.model.request.PrescriptionContentBody
import com.aya.digital.core.network.model.request.PrescriptionSubscribeBody
import com.aya.digital.core.network.model.response.AppointmentResponse
import com.aya.digital.core.network.model.response.telehealth.TelehealthRoomTokenResponse
import com.aya.digital.core.network.model.response.telehealth.TelehealthWaitTimeResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDate
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface PrescriptionsDataSource {

   fun editPrescriptionByPrescriptionId(prescriptionId: Int, prescriptionContentBody: PrescriptionContentBody): Single<Boolean>

   fun editPrescriptionByAppointmentId(appointmentId: Int, prescriptionContentBody: PrescriptionContentBody): Single<Boolean>
   fun subscribeToPrescriptions(prescriptionSubscribeBody: PrescriptionSubscribeBody): Completable

   fun getPrescriptionsByAppointmentId(appointmentId:Int): Single<Boolean>

   fun getPrescriptionById(prescriptionId:Int): Single<Boolean>

   fun getPatientPrescriptionsByPatientId(patientId:Int): Single<Boolean>

   fun getDoctorPrescriptionsByPatientId(patientId:Int): Single<Boolean>
}