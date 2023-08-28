package com.aya.digital.core.domain.schedule.doctor.scheduler.model

import android.os.Parcelable
import com.aya.digital.core.domain.schedule.base.model.SlotModelType
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class SchedulerSlotModel(
    val id: Int,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val type: SlotModelType
) : Parcelable
