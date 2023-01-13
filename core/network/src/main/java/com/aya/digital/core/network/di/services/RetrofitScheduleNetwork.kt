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
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import org.kodein.di.instance

fun scheduleNetworkModule() = DI.Module("scheduleNetworkModule") {
    bind<ScheduleDataSource>() with eagerSingleton {
        val apiService = createApiService<ScheduleService>(instance())
        return@eagerSingleton RetrofitScheduleNetwork(apiService)
    }
}

class RetrofitScheduleNetwork(network: ScheduleService) : ScheduleDataSource {
    override fun fetchSchedules(
        practitionerId: Int,
        start: LocalDate,
        end: LocalDate,
        page: Int,
        limit: Int
    ): Flowable<PagedResponse<Schedule>> {
        TODO("Not yet implemented")
    }

    override fun create(scheduleWithSlots: ScheduleWithSlotsBody): Completable {
        TODO("Not yet implemented")
    }

    override fun getSlot(id: Int): Single<SlotResponse> {
        TODO("Not yet implemented")
    }

    override fun deleteSlot(id: Int): Completable {
        TODO("Not yet implemented")
    }

    override fun createSlot(body: SlotBody): Completable {
        TODO("Not yet implemented")
    }

    override fun updateSlot(id: Int, body: SlotBody): Completable {
        TODO("Not yet implemented")
    }
}