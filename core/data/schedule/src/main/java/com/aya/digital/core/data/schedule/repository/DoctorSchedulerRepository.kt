package com.aya.digital.core.data.schedule.repository

import com.aya.digital.core.data.schedule.ScheduleSlot
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single
import java.time.LocalDateTime

interface DoctorSchedulerRepository {
    fun getSlots(
        practitionerId: Int? = null,
        start: LocalDateTime,
        end: LocalDateTime,
    ): Single<RequestResult<List<ScheduleSlot>>>

    /* "date": "2023-09-04",
 "dayStart": "10:00",
 "dayEnd": "18:00",
 "zoneOffset": "+03:00",
 "slotDuration": 60,
 "active": true,
 "slotType": "OFFLINE",
 "comment": "test schedule"
}*/
    fun createScheduleForDay(
        startTime: LocalDateTime,
        endTime:LocalDateTime,
        slotDurationMin:Int,
        slotType:String
    ):Single<RequestResult<Boolean>>
}