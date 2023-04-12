package com.aya.digital.core.domain.appointment.base.impl

import com.aya.digital.core.data.appointment.Appointment
import com.aya.digital.core.data.appointment.repository.AppointmentRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.domain.appointment.base.GetAppointmentsUseCase
import com.aya.digital.core.domain.appointment.base.model.AppointmentModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable

internal class GetAppointmentsUseCaseImpl(private val appointmentRepository: AppointmentRepository) :
    GetAppointmentsUseCase {
    override fun invoke(): Flowable<RequestResultModel<List<AppointmentModel>>> =
        appointmentRepository.getAppointments()
            .mapResult({ it.map { item -> item.toAppointmentModel() }.asResultModel() },
                { it.toModelError() })

    private fun Appointment.toAppointmentModel() =
        AppointmentModel(
            id = this.id,
            comment = this.comment,
            createdAt = this.createdAt,
            startDate = this.startDate,
            endDate = this.endDate,
            minutesDuration = this.minutesDuration
        )
}