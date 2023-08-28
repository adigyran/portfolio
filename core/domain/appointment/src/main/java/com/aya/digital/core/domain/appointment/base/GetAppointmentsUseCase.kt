package com.aya.digital.core.domain.appointment.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import io.reactivex.rxjava3.core.Flowable
import java.time.LocalDate
import java.time.LocalDateTime

interface GetAppointmentsUseCase {
    operator fun invoke(
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime
    ): Flowable<RequestResultModel<List<AppointmentModel>>>

}