package com.aya.digital.core.domain.appointment.base.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.appointment.base.GetAppointmentsUseCase
import com.aya.digital.core.domain.appointment.base.GetAppointmentsWithParticipantsByStartDayAndDaysUseCase
import com.aya.digital.core.domain.appointment.base.GetAppointmentsWithParticipantsUseCase
import com.aya.digital.core.domain.appointment.base.ext.calculateDaysInterval
import com.aya.digital.core.domain.appointment.base.model.AppointmentWithParticipantModel
import io.reactivex.rxjava3.core.Flowable
import java.time.LocalDate

internal class GetAppointmentsWithParticipantsByStartDayAndDaysUseCaseImpl(
    private val getAppointmentsWithParticipantsUseCase: GetAppointmentsWithParticipantsUseCase
) :
    GetAppointmentsWithParticipantsByStartDayAndDaysUseCase {
    override fun invoke(
        startDate: LocalDate?,
        days: Long?
    ): Flowable<RequestResultModel<List<AppointmentWithParticipantModel>>> = kotlin.run {
        val startEndInterval = startDate.calculateDaysInterval(days)
        getAppointmentsWithParticipantsUseCase(
            startDateTime = startEndInterval.startDateTime,
            endDateTime = startEndInterval.endDateTime
        )
    }

}