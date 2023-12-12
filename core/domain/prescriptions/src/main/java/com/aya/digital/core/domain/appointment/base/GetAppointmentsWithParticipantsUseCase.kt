package com.aya.digital.core.domain.appointment.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.appointment.base.model.AppointmentWithParticipantModel
import io.reactivex.rxjava3.core.Flowable
import java.time.LocalDateTime

interface GetAppointmentsWithParticipantsUseCase {
    operator fun invoke(startDateTime: LocalDateTime,
                        endDateTime: LocalDateTime
    ): Flowable<RequestResultModel<List<AppointmentWithParticipantModel>>>

}