package com.aya.digital.core.domain.appointment.base.impl

import com.aya.digital.core.data.appointment.repository.AppointmentRepository
import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.appointment.base.GetAppointmentsUseCase
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import com.aya.digital.core.domain.base.models.appointment.toAppointmentModel
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

internal class GetAppointmentsUseCaseImpl(
    private val appointmentRepository: AppointmentRepository,
    private val progressRepository: ProgressRepository
) :
    GetAppointmentsUseCase {
    override fun invoke(startDateTime: LocalDateTime,
                        endDateTime: LocalDateTime
    ): Flowable<RequestResultModel<List<AppointmentModel>>> {
        return appointmentRepository.getAppointments(startDateTime, endDateTime)
            .trackProgress(progressRepository)
            .mapResult({ it.map { item -> item.toAppointmentModel() }.asResultModel() },
                { it.toModelError() })


    }




}