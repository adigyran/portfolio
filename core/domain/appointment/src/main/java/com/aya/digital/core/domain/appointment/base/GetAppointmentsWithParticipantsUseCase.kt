package com.aya.digital.core.domain.appointment.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.appointment.base.model.AppointmentWithParticipantModel
import io.reactivex.rxjava3.core.Flowable
import kotlinx.datetime.LocalDate

interface GetAppointmentsWithParticipantsUseCase {
    operator fun invoke(date: LocalDate? = null): Flowable<RequestResultModel<List<AppointmentWithParticipantModel>>>

}