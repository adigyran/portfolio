package com.aya.digital.core.data.appointment

import kotlin.time.Duration

data class TelehealthWaitTimeModel(
    val beforeTimeout: Duration,
    val afterTimeout: Duration?,
    val globalAppointmentTimeout:Boolean
)