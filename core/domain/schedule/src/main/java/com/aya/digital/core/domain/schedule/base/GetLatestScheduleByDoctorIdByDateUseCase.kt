package com.aya.digital.core.domain.schedule.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.schedule.base.model.ScheduleSlotModel
import io.reactivex.rxjava3.core.Flowable
import kotlinx.datetime.LocalDate

interface GetLatestScheduleByDoctorIdByDateUseCase {
    operator fun invoke(doctorId:Int,date:LocalDate): Flowable<RequestResultModel<List<ScheduleSlotModel>>>

}