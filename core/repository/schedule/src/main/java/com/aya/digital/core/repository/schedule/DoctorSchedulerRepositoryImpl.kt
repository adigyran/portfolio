package com.aya.digital.core.repository.schedule

import com.aya.digital.core.data.schedule.Schedule
import com.aya.digital.core.data.schedule.ScheduleSlot
import com.aya.digital.core.data.schedule.mappers.ScheduleMapper
import com.aya.digital.core.data.schedule.mappers.ScheduleSlotMapper
import com.aya.digital.core.data.schedule.repository.DoctorSchedulerRepository
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
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

internal class DoctorSchedulerRepositoryImpl(
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

}