package com.aya.digital.core.domain.appointment.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import io.reactivex.rxjava3.core.Flowable

interface GetAppointmentsUseCase {
    operator fun invoke(): Flowable<RequestResultModel<List<AppointmentModel>>>

}