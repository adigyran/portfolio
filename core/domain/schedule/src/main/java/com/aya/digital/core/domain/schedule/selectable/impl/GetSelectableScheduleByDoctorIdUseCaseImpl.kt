package com.aya.digital.core.domain.schedule.selectable.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.schedule.Schedule
import com.aya.digital.core.data.schedule.repository.ScheduleRepository
import com.aya.digital.core.domain.schedule.selectable.GetSelectableScheduleByDoctorIdUseCase
import com.aya.digital.core.domain.schedule.selectable.ScheduleModel
import com.aya.digital.core.domain.schedule.selectable.toScheduleModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable
import kotlinx.datetime.*

internal class GetSelectableScheduleByDoctorIdUseCaseImpl(private val repository: ScheduleRepository) : GetSelectableScheduleByDoctorIdUseCase {
    override fun invoke(
        doctorId: Int,
        days: Int
    ): Flowable<RequestResultModel<List<ScheduleModel>>>{
        val currentInstant = Clock.System.now()
        val systemTZ = TimeZone.currentSystemDefault()
        val startDateTime = currentInstant.toLocalDateTime(systemTZ)
        val endDateTime =  currentInstant.plus(days, DateTimeUnit.DAY,systemTZ).toLocalDateTime(systemTZ)
        return repository.getSelectableSchedule(doctorId,startDateTime,endDateTime)
            .mapResult({selectableSlots -> selectableSlots.map(Schedule::toScheduleModel).asResultModel()},{it.toModelError()})
    }

}