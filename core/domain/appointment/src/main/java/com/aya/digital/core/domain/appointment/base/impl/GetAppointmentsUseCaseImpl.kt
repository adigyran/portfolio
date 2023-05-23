package com.aya.digital.core.domain.appointment.base.impl

import com.aya.digital.core.data.appointment.repository.AppointmentRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.domain.appointment.base.GetAppointmentsUseCase
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import com.aya.digital.core.domain.base.models.appointment.toAppointmentModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

internal class GetAppointmentsUseCaseImpl(private val appointmentRepository: AppointmentRepository) :
    GetAppointmentsUseCase {
    override fun invoke(): Flowable<RequestResultModel<List<AppointmentModel>>> {
        val currentInstant = Clock.System.now()
        val systemTZ = TimeZone.currentSystemDefault()
        val startDateTime = currentInstant.toLocalDateTime(systemTZ)
        val endDateTime =
            currentInstant.plus(100, DateTimeUnit.DAY, systemTZ).toLocalDateTime(systemTZ)
        return appointmentRepository.getAppointments(startDateTime, endDateTime/**/)
            .mapResult({ it.map { item -> item.toAppointmentModel() }.asResultModel() },
                { it.toModelError() })
    }


}