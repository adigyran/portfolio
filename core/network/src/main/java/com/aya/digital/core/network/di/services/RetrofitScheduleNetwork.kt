package com.aya.digital.core.network.di.services

import com.aya.digital.core.network.api.services.ScheduleService
import com.aya.digital.core.network.datasources.ScheduleDataSource
import com.aya.digital.core.network.di.createApiService
import com.aya.digital.core.network.model.request.ScheduleWithSlotsBody
import com.aya.digital.core.network.model.request.SlotBody
import com.aya.digital.core.network.model.response.SlotResponse
import com.aya.digital.core.network.model.response.base.PagedResponse
import com.aya.digital.core.network.model.response.schedule.Schedule
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDate
import org.kodein.di.*

fun scheduleNetworkModule() = DI.Module("scheduleNetworkModule") {
    bind<ScheduleDataSource>() with singleton {
        val apiService = createApiService<ScheduleService>(instance())
        return@singleton RetrofitScheduleNetwork(apiService)
    }
}

class RetrofitScheduleNetwork(private val network: ScheduleService) : ScheduleDataSource {
    override fun fetchSchedules(
        practitionerId: Int,
        start: LocalDate,
        end: LocalDate,
        page: Int,
        limit: Int
    ): Flowable<PagedResponse<Schedule>> = network.fetchSchedules(practitionerId, start, end, page, limit)

    override fun create(scheduleWithSlots: ScheduleWithSlotsBody): Completable = network.create(scheduleWithSlots)

    override fun getSlot(id: Int): Single<SlotResponse> = network.getSlot(id)

    override fun deleteSlot(id: Int): Completable = network.deleteSlot(id)

    override fun createSlot(body: SlotBody): Completable = network.createSlot(body)

    override fun updateSlot(id: Int, body: SlotBody): Completable = network.updateSlot(id, body)
}