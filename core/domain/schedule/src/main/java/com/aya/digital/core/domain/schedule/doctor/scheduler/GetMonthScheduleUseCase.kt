package com.aya.digital.core.domain.schedule.doctor.scheduler

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.schedule.base.model.ScheduleSlotModel
import com.aya.digital.core.domain.schedule.doctor.scheduler.model.ScheduleDayModel
import com.aya.digital.core.domain.schedule.patient.selectable.model.ScheduleModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import java.time.Month
import java.time.YearMonth

interface GetMonthScheduleUseCase {
    operator fun invoke(month: YearMonth): Single<RequestResultModel<List<ScheduleDayModel>>>

}