package com.aya.digital.core.domain.appointment.base.impl

import com.aya.digital.core.data.appointment.repository.AppointmentRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.domain.appointment.base.CancelAppointmentByIdUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class CancelAppointmentByIdUseCaseImpl(private val appointmentRepository: AppointmentRepository) : CancelAppointmentByIdUseCase {
    override fun invoke(appointmentId: Int): Single<RequestResultModel<Boolean>> =
        appointmentRepository.cancelAppointment(appointmentId)
            .mapResult({it.asResultModel()},{it.toModelError()})
}