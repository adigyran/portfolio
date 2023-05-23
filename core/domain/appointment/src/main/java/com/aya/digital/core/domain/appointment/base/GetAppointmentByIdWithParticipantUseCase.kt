package com.aya.digital.core.domain.appointment.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.appointment.base.model.AppointmentWithParticipantModel
import io.reactivex.rxjava3.core.Single

interface GetAppointmentByIdWithParticipantUseCase {
    operator fun invoke(appointmentId:Int): Single<RequestResultModel<AppointmentWithParticipantModel>>

}