package com.aya.digital.core.domain.schedule.doctor.scheduler.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ScheduleDayModel(
    val date: LocalDate
) : Parcelable
