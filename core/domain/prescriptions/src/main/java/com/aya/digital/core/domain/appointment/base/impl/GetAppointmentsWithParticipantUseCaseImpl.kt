package com.aya.digital.core.domain.appointment.base.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.flatMapResult
import com.aya.digital.core.data.base.dataprocessing.mapResult
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.appointment.base.GetAppointmentsUseCase
import com.aya.digital.core.domain.appointment.base.GetAppointmentsWithParticipantsUseCase
import com.aya.digital.core.domain.appointment.base.model.AppointmentWithParticipantModel
import com.aya.digital.core.domain.appointment.participants.GetParticipantByIdUseCase
import com.aya.digital.core.domain.base.models.progress.trackProgress
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiConsumer

import kotlinx.datetime.toJavaLocalDate
import java.time.LocalDateTime


internal class GetAppointmentsWithParticipantUseCaseImpl(
    private val getAppointmentsUseCase: GetAppointmentsUseCase,
    private val getParticipantByIdUseCase: GetParticipantByIdUseCase,
    private val progressRepository: ProgressRepository
) : GetAppointmentsWithParticipantsUseCase {
    override fun invoke(
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime
    ): Flowable<RequestResultModel<List<AppointmentWithParticipantModel>>> =
        getAppointmentsUseCase(startDateTime = startDateTime, endDateTime = endDateTime)
            .trackProgress(progressRepository)
            .flatMapResult({ appointmentModels ->
                Flowable.fromIterable(appointmentModels)
                    .flatMapSingle { appointmentModel ->
                        appointmentModel.participantId?.let { participantId ->
                            getParticipantByIdUseCase(participantId)
                                .mapResult({ appointmentParticipant ->
                                    AppointmentWithParticipantModel(
                                        appointmentModel = appointmentModel,
                                        appointmentParticipant = appointmentParticipant
                                    ).asResultModel()

                                }, { it })
                        }
                            ?: Single.just(AppointmentWithParticipantModel(appointmentModel).asResultModel())
                    }
                    .collectInto(
                        mutableListOf<AppointmentWithParticipantModel>(),
                        BiConsumer { t1, t2 ->
                            t2.processResult({ t1.add(it) }, {})
                        })
                    .map { it.asResultModel() }
                    .toFlowable()
            }, { Flowable.just(it) })
            .mapResult({ it.asResultModel() }, { it })
}