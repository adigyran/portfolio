package com.aya.digital.core.domain.appointment.telehealth

import kotlin.time.Duration

data class TelehealthTimeWindowModel(val beforeTimeout: Duration,
                                     val afterTimeout: Duration?,
                                     val globalAppointmentTimeout:Boolean)
