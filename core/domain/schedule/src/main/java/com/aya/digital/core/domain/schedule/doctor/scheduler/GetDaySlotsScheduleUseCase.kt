package com.aya.digital.core.domain.schedule.doctor.scheduler

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.schedule.doctor.scheduler.model.ScheduleDayModel
import com.aya.digital.core.domain.schedule.doctor.scheduler.model.SchedulerSlotModel
import io.reactivex.rxjava3.core.Single
import java.time.LocalDate

interface GetDaySlotsScheduleUseCase {
    operator fun invoke(day:LocalDate): Single<RequestResultModel<List<SchedulerSlotModel>>>

}