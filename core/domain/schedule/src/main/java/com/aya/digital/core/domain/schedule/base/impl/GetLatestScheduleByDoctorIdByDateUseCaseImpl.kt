package com.aya.digital.core.domain.schedule.base.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.schedule.ScheduleSlot
import com.aya.digital.core.data.schedule.repository.ScheduleRepository
import com.aya.digital.core.domain.schedule.base.GetLatestScheduleByDoctorIdByDateUseCase
import com.aya.digital.core.domain.schedule.base.model.ScheduleSlotModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Flowable
import kotlinx.datetime.*

internal class GetLatestScheduleByDoctorIdByDateUseCaseImpl(private val repository: ScheduleRepository) :
    GetLatestScheduleByDoctorIdByDateUseCase {
    override fun invoke(
        doctorId: Int, date: LocalDate
    ): Flowable<RequestResultModel<List<ScheduleSlotModel>>> {
        val startDateTime = date.atStartOfDayIn(TimeZone.currentSystemDefault())
            .toLocalDateTime(TimeZone.currentSystemDefault())
        val endDateTime =
            startDateTime.toInstant(TimeZone.currentSystemDefault()).plus(24, DateTimeUnit.HOUR)
                .toLocalDateTime(
                    TimeZone.currentSystemDefault()
                )
        return repository.getSlots(doctorId, startDateTime, endDateTime)
            .mapResult({ scheduleSlots -> scheduleSlots.map { it.toSlotModel() }.asResultModel() },
                { it.toModelError() })
    }

    private fun ScheduleSlot.toSlotModel() = ScheduleSlotModel(
        id = id,
        startDate = startDate,
        endDate = endDate,
        status = statusSlot,
        overBooked = overBooked,
        comment = commentSlot
    )
}