package com.aya.digital.core.domain.appointment.participants

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.appointment.participants.model.AppointmentParticipant
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface GetParticipantByIdUseCase {
    operator fun invoke(participantId:Int): Single<RequestResultModel<AppointmentParticipant>>

}