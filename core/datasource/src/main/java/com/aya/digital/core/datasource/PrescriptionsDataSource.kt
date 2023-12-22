package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.request.PrescriptionContentBody
import com.aya.digital.core.network.model.request.PrescriptionSubscribeBody
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface PrescriptionsDataSource {

   fun editPrescriptionByPrescriptionId(prescriptionId: Int, prescriptionContentBody: PrescriptionContentBody): Single<Boolean>

   fun editPrescriptionByAppointmentId(appointmentId: Int, prescriptionContentBody: PrescriptionContentBody): Single<Boolean>
   fun subscribeToPrescriptions(prescriptionSubscribeBody: PrescriptionSubscribeBody): Completable

   fun getPrescriptionsByAppointmentId(appointmentId:Int): Single<Boolean>

   fun getPrescriptionById(prescriptionId:Int): Single<Boolean>

   fun getPatientPrescriptionsByPatientId(patientId:Int): Single<Boolean>

   fun getDoctorPrescriptionsByPractitionerId(practitionerId:Int): Single<Boolean>
}