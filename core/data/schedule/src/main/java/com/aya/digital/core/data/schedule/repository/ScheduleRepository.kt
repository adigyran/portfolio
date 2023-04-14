package com.aya.digital.core.data.schedule.repository

import com.aya.digital.core.data.base.dataprocessing.PaginationPageModel
import com.aya.digital.core.data.schedule.Schedule
import com.aya.digital.core.data.schedule.ScheduleSlot
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

interface ScheduleRepository {
    fun getSlots(
        practitionerId: Int,
        start: LocalDateTime,
        end: LocalDateTime,
    ): Flowable<RequestResult<List<ScheduleSlot>>>

}