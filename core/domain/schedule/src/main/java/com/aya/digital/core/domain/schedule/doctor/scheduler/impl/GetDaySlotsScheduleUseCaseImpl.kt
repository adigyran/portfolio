package com.aya.digital.core.domain.schedule.doctor.scheduler.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.data.schedule.ScheduleSlot
import com.aya.digital.core.data.schedule.repository.DoctorSchedulerRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.schedule.base.model.getSlotType
import com.aya.digital.core.domain.schedule.doctor.scheduler.GetDaySlotsScheduleUseCase
import com.aya.digital.core.domain.schedule.doctor.scheduler.model.SchedulerSlotModel
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single
import java.time.LocalDate

internal class GetDaySlotsScheduleUseCaseImpl(
    private val profileRepository: ProfileRepository,
    private val schedulerRepository: DoctorSchedulerRepository,
    private val progressRepository: ProgressRepository
) : GetDaySlotsScheduleUseCase {
    override fun invoke(day: LocalDate): Single<RequestResultModel<List<SchedulerSlotModel>>> =
        getDaysSlots(null, day)
            .mapResult({ slots -> slots.map { it.toSlotModel() }.asResultModel() }, { it.toModelError() })

    private fun getDaysSlots(profileId: Int?, day: LocalDate) = schedulerRepository.getSlots(
        practitionerId = profileId,
        start = day.atStartOfDay(),
        end = day.atTime(23, 59, 59)
    )
        .trackProgress(progressRepository)

    private fun ScheduleSlot.toSlotModel() = SchedulerSlotModel(
        id = id,
        startDateTime = startDate,
        endDateTime = endDate,
        type = type.getSlotType()
    )

}
