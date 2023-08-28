package com.aya.digital.core.domain.schedule.patient.selectable

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.schedule.base.model.ScheduleSlotModel
import com.aya.digital.core.domain.schedule.patient.selectable.model.ScheduleModel
import io.reactivex.rxjava3.core.Flowable

interface GetSelectableScheduleByDoctorIdUseCase {
    operator fun invoke(doctorId:Int,days:Int): Flowable<RequestResultModel<List<ScheduleModel>>>

}