package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.request.ScheduleWithSlotsBody
import com.aya.digital.core.network.model.request.SlotBody
import com.aya.digital.core.network.model.response.schedule.ScheduleResponse
import com.aya.digital.core.network.model.response.schedule.SlotResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface ScheduleDataSource {

    fun getSelectableDoctorSchedule(
        practitionerId: Int,
        start: String,
        end: String
    ): Flowable<List<ScheduleResponse>>

    fun fetchDoctorSlots(
        practitionerId: Int,
        start: String,
        end: String
    ): Flowable<List<SlotResponse>>

    fun fetchSlots(
        practitionerId: Int?,
        start: String,
        end: String
    ):Single<List<SlotResponse>>

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