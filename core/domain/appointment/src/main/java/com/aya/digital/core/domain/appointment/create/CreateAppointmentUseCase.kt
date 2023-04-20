package com.aya.digital.core.domain.appointment.create

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.appointment.base.model.AppointmentModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface CreateAppointmentUseCase {
    operator fun invoke(slotId:Int,comment:String): Single<RequestResultModel<AppointmentModel>>
}