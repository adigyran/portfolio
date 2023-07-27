package com.aya.digital.core.repository.schedule

import com.aya.digital.core.data.schedule.Schedule
import com.aya.digital.core.data.schedule.ScheduleSlot
import com.aya.digital.core.data.schedule.mappers.ScheduleMapper
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
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

internal class ScheduleRepositoryImpl(
    private val scheduleDataSource: ScheduleDataSource,
    private val scheduleSlotMapper: ScheduleSlotMapper,
    private val scheduleMapper: ScheduleMapper
) : ScheduleRepository {
    override fun getSlots(
        practitionerId: Int,
        start: LocalDateTime,
        end: LocalDateTime
    ): Flowable<RequestResult<List<ScheduleSlot>>> =
        scheduleDataSource.fetchSlots(
            practitionerId,
            start.toInstant(TimeZone.currentSystemDefault()).toString(),
            end.toInstant(
                TimeZone.currentSystemDefault()
            ).toString()
        )
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result -> scheduleSlotMapper.mapFromList(result).asResult() }, { it })

    override fun getSelectableSchedule(
        practitionerId: Int,
        start: LocalDateTime,
        end: LocalDateTime
    ): Flowable<RequestResult<List<Schedule>>> =
        scheduleDataSource.getSelectableSchedule(
            practitionerId,
            start.toInstant(TimeZone.currentSystemDefault()).toString(),
            end.toInstant(
                TimeZone.currentSystemDefault()
            ).toString()
        )
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result -> scheduleMapper.mapFromList(result).asResult() }, { it })
}