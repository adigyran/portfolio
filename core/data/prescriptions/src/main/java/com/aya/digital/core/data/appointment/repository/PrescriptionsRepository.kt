package com.aya.digital.core.data.appointment.repository


import com.aya.digital.core.network.model.request.PrescriptionContentBody
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import java.time.LocalDateTime

interface PrescriptionsRepository {

  fun editPrescriptionByPrescriptionId(prescriptionId: Int, content:String): Single<RequestResult<Boolean>>

  fun editPrescriptionByAppointmentId(appointmentId: Int, content:String): Single<RequestResult<Boolean>>
  fun subscribeToPrescriptions(practitionerId: Int, patientId: Int, appointmentId: Int):Single<RequestResult<Boolean>>

  fun getPrescriptionsByAppointmentId(appointmentId:Int): Single<RequestResult<Boolean>>

  fun getPrescriptionById(prescriptionId:Int): Single<RequestResult<Boolean>>

  fun getPatientPrescriptionsByPatientId(patientId:Int): Single<RequestResult<Boolean>>

  fun getDoctorPrescriptionsByPatientId(patientId:Int): Single<RequestResult<Boolean>>

}