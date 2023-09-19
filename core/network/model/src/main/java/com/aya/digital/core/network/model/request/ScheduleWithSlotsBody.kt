package com.aya.digital.core.network.model.request

import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate


/*{
 "date": "2023-09-04",
 "dayStart": "10:00",
 "dayEnd": "18:00",
 "zoneOffset": "+03:00",
 "slotDuration": 60,
 "active": true,
 "slotType": "OFFLINE",
 "comment": "test schedule"
}*/
@JsonClass(generateAdapter = true)
data class ScheduleWithSlotsBody(
    val date:String,
    val dayStart: String,
    val dayEnd: String,
    val active: Boolean,
    val zoneOffset:String,
    val slotType: String,
    val slotDuration: Int
)