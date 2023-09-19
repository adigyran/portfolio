package com.aya.digital.core.repository.schedule

import com.aya.digital.core.data.schedule.ScheduleSlot
import com.aya.digital.core.data.schedule.mappers.ScheduleMapper
import com.aya.digital.core.data.schedule.mappers.ScheduleSlotMapper
import com.aya.digital.core.data.schedule.repository.DoctorSchedulerRepository
import com.aya.digital.core.datasource.ScheduleDataSource
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.ext.retrofitResponseToResult
import com.aya.digital.core.ext.retryOnError
import com.aya.digital.core.network.model.request.ScheduleWithSlotsBody
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import com.aya.digital.core.util.datetime.DateTimeUtils
import io.reactivex.rxjava3.core.Single
import kotlinx.datetime.toKotlinLocalDateTime
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Locale


internal class DoctorSchedulerRepositoryImpl(
    private val dateTimeUtils: DateTimeUtils,
    private val scheduleDataSource: ScheduleDataSource,
    private val scheduleSlotMapper: ScheduleSlotMapper,
    private val scheduleMapper: ScheduleMapper
) : DoctorSchedulerRepository {
    override fun getSlots(
        practitionerId: Int?,
        start: LocalDateTime,
        end: LocalDateTime
    ): Single<RequestResult<List<ScheduleSlot>>> = scheduleDataSource.fetchSlots(
        practitionerId = practitionerId,
        start =  start.atZone(ZoneId.systemDefault()).toInstant().toString(),
        end =  end.atZone(ZoneId.systemDefault()).toInstant().toString(),
    )
        .retryOnError()
        .retrofitResponseToResult(CommonUtils::mapServerErrors)
        .mapResult({ result -> scheduleSlotMapper.mapFromList(result).asResult() }, { it })

    override fun createScheduleForDay(
        startTime: LocalDateTime,
        endTime: LocalDateTime,
        slotDurationMin: Int,
        slotType: String
    ): Single<RequestResult<Boolean>> {
        val start = dateTimeUtils.format24HoursTime(startTime.toKotlinLocalDateTime().time)
        val end = dateTimeUtils.format24HoursTime(endTime.toKotlinLocalDateTime().time)
        val date = dateTimeUtils.formatYmdDate(startTime.toKotlinLocalDateTime().date)
        val zoneOffset = SimpleDateFormat("ZZZZZ", Locale.getDefault()).format(System.currentTimeMillis())

        val body = ScheduleWithSlotsBody(date,start,end,true,zoneOffset,slotType,slotDurationMin)
        return scheduleDataSource.create(body)
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ result -> result.asResult()}, { it })
    }

}