package com.aya.digital.core.domain.schedule.base.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.data.schedule.ScheduleSlot
import com.aya.digital.core.data.schedule.repository.ScheduleRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.schedule.base.GetLatestScheduleByDoctorIdUseCase
import com.aya.digital.core.domain.schedule.base.model.ScheduleSlotModel
import com.aya.digital.core.domain.schedule.base.model.getSlotType
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable
import kotlinx.datetime.*

internal class GetLatestScheduleByDoctorIdUseCaseImpl(
    private val repository: ScheduleRepository,
    private val progressRepository: ProgressRepository
) :
    GetLatestScheduleByDoctorIdUseCase {
    override fun invoke(
        doctorId: Int,
        days: Int
    ): Flowable<RequestResultModel<List<ScheduleSlotModel>>> {
        val currentInstant = Clock.System.now()
        val systemTZ = TimeZone.currentSystemDefault()
        val startDateTime = currentInstant.toLocalDateTime(systemTZ)
        val endDateTime =
            currentInstant.plus(days, DateTimeUnit.DAY, systemTZ).toLocalDateTime(systemTZ)
        return repository.getSlots(doctorId, startDateTime.toJavaLocalDateTime(), endDateTime.toJavaLocalDateTime())
            .trackProgress(progressRepository)
            .mapResult({ scheduleSlots -> scheduleSlots.map { it.toSlotModel() }.asResultModel() },
                { it.toModelError() })
    }

    private fun ScheduleSlot.toSlotModel() =
        ScheduleSlotModel(
            id = id,
            startDate = startDate.toKotlinLocalDateTime(),
            endDate = endDate.toKotlinLocalDateTime(),
            status = statusSlot,
            overBooked = overBooked,
            comment = commentSlot,
            type = type.getSlotType()
        )
}