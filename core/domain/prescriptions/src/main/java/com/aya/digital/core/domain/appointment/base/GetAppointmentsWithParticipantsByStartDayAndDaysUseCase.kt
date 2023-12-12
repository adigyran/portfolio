package com.aya.digital.core.domain.appointment.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.appointment.base.model.AppointmentWithParticipantModel
import com.aya.digital.core.domain.base.models.appointment.AppointmentModel
import io.reactivex.rxjava3.core.Flowable
import java.time.LocalDate

interface GetAppointmentsWithParticipantsByStartDayAndDaysUseCase {
    operator fun invoke(startDate:LocalDate?=null,days:Long?=null): Flowable<RequestResultModel<List<AppointmentWithParticipantModel>>>

}