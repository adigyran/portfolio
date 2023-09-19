package com.aya.digital.core.domain.schedule.doctor.scheduler.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.data.schedule.repository.DoctorSchedulerRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.schedule.doctor.scheduler.CreateDaySlotsScheduleUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single
import java.time.LocalDateTime

internal class CreateDaySlotsScheduleUseCaseImpl(private val schedulerRepository: DoctorSchedulerRepository,
                                        private val progressRepository: ProgressRepository
) : CreateDaySlotsScheduleUseCase {
    override fun invoke(
        startTime: LocalDateTime,
        endTime: LocalDateTime,
        minutesDuration: Int,
        isTelemed: Boolean
    ): Single<RequestResultModel<Boolean>> = schedulerRepository
        .createScheduleForDay(startTime,endTime,minutesDuration,isTelemed.getScheduleType())
        .trackProgress(progressRepository)
        .mapResult({it.asResultModel()},{it.toModelError()})
}

private fun Boolean.getScheduleType() = if(this) "ONLINE" else "OFFLINE"