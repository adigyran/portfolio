package com.aya.digital.core.repository.appointment

import com.aya.digital.core.data.appointment.repository.PrescriptionsRepository
import com.aya.digital.core.datasource.PrescriptionsDataSource
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.ext.retryOnError

import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.ext.retrofitResponseToResult
import com.aya.digital.core.network.model.request.PrescriptionContentBody
import com.aya.digital.core.network.model.request.PrescriptionSubscribeBody
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single

internal class PrescriptionsRepositoryImpl(
    private val prescriptionsDataSource: PrescriptionsDataSource
) : PrescriptionsRepository {
    override fun editPrescriptionByPrescriptionId(
        prescriptionId: Int,
        content: String
    ): Single<RequestResult<Boolean>> = prescriptionsDataSource.editPrescriptionByPrescriptionId(
        prescriptionId,
        PrescriptionContentBody(content)
    )
        .retryOnError()
        .retrofitResponseToResult(CommonUtils::mapServerErrors)
        .mapResult({ result ->
            true.asResult()
        }, { it })

    override fun editPrescriptionByAppointmentId(
        appointmentId: Int,
        content: String
    ): Single<RequestResult<Boolean>> = prescriptionsDataSource.editPrescriptionByPrescriptionId(
        appointmentId,
        PrescriptionContentBody(content)
    )
        .retryOnError()
        .retrofitResponseToResult(CommonUtils::mapServerErrors)
        .mapResult({ result ->
            true.asResult()
        }, { it })

    override fun subscribeToPrescriptions(
        practitionerId: Int,
        patientId: Int,
        appointmentId: Int
    ): Single<RequestResult<Boolean>> = prescriptionsDataSource.subscribeToPrescriptions(
        PrescriptionSubscribeBody(
            practitionerId,
            patientId,
            appointmentId
        )
    )
        .retryOnError()
        .retrofitResponseToResult(CommonUtils::mapServerErrors)
        .mapResult({ result ->
            true.asResult()
        }, { it })

    override fun getPrescriptionsByAppointmentId(appointmentId: Int): Single<RequestResult<Boolean>> =
        prescriptionsDataSource.getPrescriptionsByAppointmentId(appointmentId)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                true.asResult()
            }, { it })

    override fun getPrescriptionById(prescriptionId: Int): Single<RequestResult<Boolean>> =
        prescriptionsDataSource.getPrescriptionById(prescriptionId)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                true.asResult()
            }, { it })

    override fun getPatientPrescriptionsByPatientId(patientId: Int): Single<RequestResult<Boolean>> =
        prescriptionsDataSource.getPatientPrescriptionsByPatientId(patientId)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                true.asResult()
            }, { it })

    override fun getDoctorPrescriptionsByPractitionerId(practitionerId: Int): Single<RequestResult<Boolean>> =
        prescriptionsDataSource.getDoctorPrescriptionsByPractitionerId(practitionerId)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result ->
                true.asResult()
            }, { it })

}