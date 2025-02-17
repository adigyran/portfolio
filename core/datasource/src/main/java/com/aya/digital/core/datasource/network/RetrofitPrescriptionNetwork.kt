package com.aya.digital.core.datasource.network

import com.aya.digital.core.datasource.PrescriptionsDataSource
import com.aya.digital.core.network.api.services.PrescriptionsService
import com.aya.digital.core.network.main.di.modules.createApiService
import com.aya.digital.core.network.model.request.PrescriptionContentBody
import com.aya.digital.core.network.model.request.PrescriptionSubscribeBody
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun prescriptionNetworkModule() = DI.Module("prescriptionNetworkModule") {
    bind<PrescriptionsDataSource>() with singleton {
        val apiService =
            createApiService<PrescriptionsService>(instance())
        return@singleton RetrofitPrescriptionNetwork(apiService)
    }
}

class RetrofitPrescriptionNetwork(private val networkApi: PrescriptionsService) :
    PrescriptionsDataSource {
    override fun editPrescriptionByPrescriptionId(
        prescriptionId: Int,
        prescriptionContentBody: PrescriptionContentBody
    ): Single<Boolean>  = networkApi.editPrescriptionByPrescriptionId(prescriptionId,prescriptionContentBody)

    override fun editPrescriptionByAppointmentId(
        appointmentId: Int,
        prescriptionContentBody: PrescriptionContentBody
    ): Single<Boolean> = networkApi.editPrescriptionByAppointmentId(appointmentId, prescriptionContentBody)

    override fun subscribeToPrescriptions(prescriptionSubscribeBody: PrescriptionSubscribeBody): Completable =
        networkApi.subscribeToPrescriptions(prescriptionSubscribeBody)

    override fun getPrescriptionsByAppointmentId(appointmentId: Int): Single<Boolean> =
        networkApi.getPrescriptionsByAppointmentId(appointmentId)

    override fun getPrescriptionById(prescriptionId: Int): Single<Boolean> =
        networkApi.getPrescriptionById(prescriptionId)

    override fun getPatientPrescriptionsByPatientId(patientId: Int): Single<Boolean> =
        networkApi.getPatientPrescriptionsByPatientId(patientId)

    override fun getDoctorPrescriptionsByPractitionerId(practitionerId: Int): Single<Boolean> =
        networkApi.getDoctorPrescriptionsByPractitionerId(practitionerId)
}
