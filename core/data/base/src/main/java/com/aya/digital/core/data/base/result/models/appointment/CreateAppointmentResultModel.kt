package com.aya.digital.core.data.base.result.models.appointment

import kotlinx.datetime.LocalDateTime

data class CreateAppointmentResultModel(val appointmentId:Int,val appointmentDateTime: LocalDateTime)
