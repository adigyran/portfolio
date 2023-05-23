package com.aya.digital.core.domain.appointment.create.impl

import com.aya.digital.core.data.appointment.repository.AppointmentRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.domain.appointment.create.CreateAppointmentUseCase
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import com.aya.digital.core.domain.base.models.appointment.toAppointmentModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class CreateAppointmentUseCaseImpl(private val repository: AppointmentRepository) :
    CreateAppointmentUseCase {
    override fun invoke(
        slotId: Int,
        comment: String
    ): Single<RequestResultModel<AppointmentModel>> =
        repository.createAppointment(slotId, comment)
            .mapResult({ appointment -> appointment.toAppointmentModel().asResultModel() },
                { it.toModelError() })
}