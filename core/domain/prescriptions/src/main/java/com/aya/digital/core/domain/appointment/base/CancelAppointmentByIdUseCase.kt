package com.aya.digital.core.domain.appointment.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import io.reactivex.rxjava3.core.Single

interface CancelAppointmentByIdUseCase {
    operator fun invoke(appointmentId:Int): Single<RequestResultModel<Boolean>>

}