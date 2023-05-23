package com.aya.digital.core.domain.appointment.base.impl

import com.aya.digital.core.data.appointment.repository.AppointmentRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.doctors.repository.DoctorRepository
import com.aya.digital.core.domain.appointment.base.GetAppointmentByIdWithParticipantUseCase
import com.aya.digital.core.domain.appointment.base.model.AppointmentWithParticipantModel
import com.aya.digital.core.domain.base.models.appointment.toAppointmentModel
import com.aya.digital.core.domain.base.models.doctors.mapToDoctorModel
import com.aya.digital.core.domain.base.models.patients.mapToPatientModel
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetAppointmentByIdWithPatientUseCaseImpl(
    private val appointmentRepository: AppointmentRepository,
    private val doctorRepository: DoctorRepository
) : GetAppointmentByIdWithParticipantUseCase {
    override fun invoke(appointmentId: Int): Single<RequestResultModel<AppointmentWithParticipantModel>> =
        appointmentRepository.getAppointmentById(appointmentId)
            .flatMapResult({ appointment ->
                appointment.participant?.id?.let { patientId ->
                    doctorRepository.getPatient(patientId)
                        .mapResult({ patientData ->
                            AppointmentWithParticipantModel(
                                appointmentModel = appointment.toAppointmentModel(),
                                patientModel = patientData.mapToPatientModel()
                            ).asResult()
                        }, { it })
                        .doAfterSuccess { it }
                }
                    ?: Single.just(AppointmentWithParticipantModel(appointmentModel = appointment.toAppointmentModel()).asResult())

            }, { Single.just(it) })
            .mapResult({ it.asResultModel() }, { it.toModelError() })

}