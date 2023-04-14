package com.aya.digital.core.domain.schedule.base

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.schedule.base.model.ScheduleSlotModel
import io.reactivex.rxjava3.core.Flowable

interface GetLatestScheduleByDoctorIdUseCase {
    operator fun invoke(doctorId:Int,days:Int): Flowable<RequestResultModel<List<ScheduleSlotModel>>>

}