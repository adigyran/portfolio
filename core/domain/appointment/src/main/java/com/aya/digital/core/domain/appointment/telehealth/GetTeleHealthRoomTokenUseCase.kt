package com.aya.digital.core.domain.appointment.telehealth

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.appointment.base.model.AppointmentModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface GetTeleHealthRoomTokenUseCase {
    operator fun invoke(roomId: Int): Single<RequestResultModel<String>>

}