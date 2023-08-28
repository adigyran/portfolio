package com.aya.digital.core.domain.schedule.patient.selectable.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.data.schedule.Schedule
import com.aya.digital.core.data.schedule.repository.ScheduleRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.schedule.patient.selectable.GetSelectableScheduleByDoctorIdUseCase
import com.aya.digital.core.domain.schedule.patient.selectable.model.ScheduleModel
import com.aya.digital.core.domain.schedule.patient.selectable.model.toScheduleModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable
import kotlinx.datetime.*

internal class GetSelectableScheduleByDoctorIdUseCaseImpl(
    private val repository: ScheduleRepository,
    private val progressRepository: ProgressRepository
) : GetSelectableScheduleByDoctorIdUseCase {
    override fun invoke(
        doctorId: Int,
        days: Int
    ): Flowable<RequestResultModel<List<ScheduleModel>>> {
        val currentInstant = Clock.System.now()
        val systemTZ = TimeZone.currentSystemDefault()
        val startDateTime = currentInstant.toLocalDateTime(systemTZ)
        val endDateTime =
            currentInstant.plus(days, DateTimeUnit.DAY, systemTZ).toLocalDateTime(systemTZ)
        return repository.getSelectableSchedule(doctorId, startDateTime.toJavaLocalDateTime(), endDateTime.toJavaLocalDateTime())
            .trackProgress(progressRepository)
            .mapResult({ selectableSlots ->
                selectableSlots.map(Schedule::toScheduleModel).asResultModel()
            }, { it.toModelError() })
    }

}