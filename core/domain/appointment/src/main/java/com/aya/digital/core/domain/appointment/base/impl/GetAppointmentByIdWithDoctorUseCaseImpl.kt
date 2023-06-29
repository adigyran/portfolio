package com.aya.digital.core.domain.appointment.base.impl

import com.aya.digital.core.data.appointment.repository.AppointmentRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.doctors.repository.DoctorRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.appointment.base.GetAppointmentByIdWithParticipantUseCase
import com.aya.digital.core.domain.appointment.base.model.AppointmentWithParticipantModel
import com.aya.digital.core.domain.base.models.appointment.toAppointmentModel
import com.aya.digital.core.domain.base.models.doctors.mapToDoctorModel
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetAppointmentByIdWithDoctorUseCaseImpl(
    private val appointmentRepository: AppointmentRepository,
    private val doctorRepository: DoctorRepository,
    private val progressRepository: ProgressRepository
) : GetAppointmentByIdWithParticipantUseCase {
    override fun invoke(appointmentId: Int): Single<RequestResultModel<AppointmentWithParticipantModel>> =
        appointmentRepository.getAppointmentById(appointmentId)
            .flatMapResult({ appointment ->
                appointment.participant?.id?.let { doctorId ->
                    doctorRepository.fetchDoctorById(doctorId)
                        .mapResult({ doctorData ->
                            AppointmentWithParticipantModel(
                                appointmentModel = appointment.toAppointmentModel(),
                                doctorModel = doctorData.mapToDoctorModel()
                            ).asResult()
                        }, { it })
                        .doAfterSuccess { it }
                }
                    ?: Single.just(AppointmentWithParticipantModel(appointmentModel = appointment.toAppointmentModel()).asResult())

            }, { Single.just(it) })
            .trackProgress(progressRepository)
            .mapResult({ it.asResultModel() }, { it.toModelError() })
}