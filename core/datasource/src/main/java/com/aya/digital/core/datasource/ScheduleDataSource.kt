package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.request.ScheduleWithSlotsBody
import com.aya.digital.core.network.model.request.SlotBody
import com.aya.digital.core.network.model.response.SlotResponse
import com.aya.digital.core.network.model.response.base.PagedResponse
import com.aya.digital.core.network.model.response.schedule.Schedule
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDate

interface ScheduleDataSource {

    fun fetchSchedules(
        practitionerId: Int,
        start: LocalDate,
        end: LocalDate,
        page: Int,
        limit: Int,
    ): Flowable<PagedResponse<Schedule>>

    fun create(
        scheduleWithSlots: ScheduleWithSlotsBody
    ): Completable

    fun getSlot(
        id: Int
    ): Single<SlotResponse>

    fun deleteSlot(
        id: Int
    ): Completable

    fun createSlot(
        body: SlotBody
    ): Completable

    fun updateSlot(
        id: Int,
        body: SlotBody
    ): Completable
}