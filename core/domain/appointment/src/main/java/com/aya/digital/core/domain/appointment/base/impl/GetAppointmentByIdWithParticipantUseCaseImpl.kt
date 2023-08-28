package com.aya.digital.core.domain.appointment.base.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.flatMapResult
import com.aya.digital.core.data.base.dataprocessing.mapResult
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.appointment.base.GetAppointmentByIdUseCase
import com.aya.digital.core.domain.appointment.base.GetAppointmentByIdWithParticipantUseCase
import com.aya.digital.core.domain.appointment.base.model.AppointmentWithParticipantModel
import com.aya.digital.core.domain.appointment.participants.GetParticipantByIdUseCase
import com.aya.digital.core.domain.base.models.progress.trackProgress
import io.reactivex.rxjava3.core.Single

internal class GetAppointmentByIdWithParticipantUseCaseImpl(
    private val getAppointmentByIdUseCase: GetAppointmentByIdUseCase,
    private val getParticipantByIdUseCase: GetParticipantByIdUseCase,
    private val progressRepository: ProgressRepository
) : GetAppointmentByIdWithParticipantUseCase {
    override fun invoke(appointmentId: Int): Single<RequestResultModel<AppointmentWithParticipantModel>> =
        getAppointmentByIdUseCase(appointmentId)
            .trackProgress(progressRepository)
            .flatMapResult({ appointment ->
                appointment.participantId?.let { participantId ->
                    getParticipantByIdUseCase(participantId)
                        .mapResult({ appointmentParticipant ->
                            AppointmentWithParticipantModel(
                                appointmentModel = appointment,
                                appointmentParticipant = appointmentParticipant
                            ).asResultModel()

                        }, { it })
                } ?: Single.just(AppointmentWithParticipantModel(appointmentModel = appointment).asResultModel())

            }, { Single.just(it) })

}