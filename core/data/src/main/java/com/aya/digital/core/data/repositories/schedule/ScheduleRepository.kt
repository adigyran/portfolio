package com.aya.digital.core.data.repositories.schedule

import com.aya.digital.core.data.model.PaginationPageModel
import com.aya.digital.core.data.model.schedule.Schedule
import com.aya.digital.core.data.model.schedule.ScheduleSlot
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

interface ScheduleRepository {

    fun createWithSlots(
        practitionerId: Int,
        days: List<LocalDate>,
        dayStart: String,
        dayEnd: String,
        type: String,
        active: Boolean,
        duration: Int
    ): Single<RequestResult<Void>>

    fun fetchSchedule(
        practitionerId: Int,
        start: LocalDate,
        end: LocalDate,
        page: Int,
        limit: Int = 50,
    ): Flowable<RequestResult<PaginationPageModel<Schedule>>>

    fun getSlot(
        id: Int
    ): Single<RequestResult<ScheduleSlot>>

    fun deleteSlot(
        id: Int
    ): Single<RequestResult<Void>>

    fun createSlot(
        doctorId: Int,
        scheduleId: Int?,
        type: String?,
        slotStart: LocalDateTime,
        slotEnd: LocalDateTime,
        comment: String?,
        status: String,
        date: LocalDateTime,
    ): Single<RequestResult<Void>>

    fun updateSlot(
        id: Int,
        scheduleId: Int,
        type: String?,
        slotStart: LocalDateTime,
        slotEnd: LocalDateTime,
        comment: String?,
        status: String,
        date: LocalDateTime,
    ): Single<RequestResult<Void>>
}