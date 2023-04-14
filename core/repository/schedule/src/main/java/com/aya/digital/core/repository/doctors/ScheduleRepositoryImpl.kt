package com.aya.digital.core.repository.doctors

import com.aya.digital.core.data.schedule.ScheduleSlot
import com.aya.digital.core.data.schedule.mappers.ScheduleSlotMapper
import com.aya.digital.core.data.schedule.repository.ScheduleRepository
import com.aya.digital.core.datasource.PractitionersDataSource
import com.aya.digital.core.datasource.ScheduleDataSource
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.ext.retrofitResponseToResult
import com.aya.digital.core.ext.retryOnError
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

internal class ScheduleRepositoryImpl(
    private val scheduleDataSource: ScheduleDataSource,
    private val scheduleSlotMapper:ScheduleSlotMapper
) : ScheduleRepository {
    override fun getSlots(
        practitionerId: Int,
        start: LocalDateTime,
        end: LocalDateTime
    ): Flowable<RequestResult<List<ScheduleSlot>>> =
        scheduleDataSource.fetchSlots(practitionerId,start.toString(),end.toString())
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({result-> scheduleSlotMapper.mapFromList(result).asResult()   },{it})
}