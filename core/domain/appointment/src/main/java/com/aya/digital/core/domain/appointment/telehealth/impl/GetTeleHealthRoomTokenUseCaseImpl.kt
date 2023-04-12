package com.aya.digital.core.domain.appointment.telehealth.impl

import com.aya.digital.core.data.appointment.repository.AppointmentRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.domain.appointment.telehealth.GetTeleHealthRoomTokenUseCase
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetTeleHealthRoomTokenUseCaseImpl(private val appointmentRepository: AppointmentRepository) : GetTeleHealthRoomTokenUseCase {
    override fun invoke(roomId: Int): Single<RequestResultModel<String>> =
        appointmentRepository.getRoomTokenById(roomId)
            .mapResult({it.asResultModel()},{it.toModelError()})
}