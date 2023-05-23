package com.aya.digital.core.domain.appointment.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface GetAppointmentByIdUseCase {
    operator fun invoke(appointmentId:Int): Single<RequestResultModel<AppointmentModel>>

}