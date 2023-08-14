package com.aya.digital.core.mappers.appointment

import com.aya.digital.core.data.appointment.AppoinmentSlot
import com.aya.digital.core.data.appointment.TelehealthWaitTimeModel
import com.aya.digital.core.data.appointment.mappers.PatientMapper
import com.aya.digital.core.data.appointment.mappers.TelehealthWaitTimeMapper
import com.aya.digital.core.network.model.response.schedule.SlotResponse
import com.aya.digital.core.network.model.response.telehealth.TelehealthWaitTimeResponse
import java.time.Duration
import kotlin.time.toKotlinDuration

internal class TelehealthWaitTimeMapperImpl : TelehealthWaitTimeMapper() {
    override fun mapFrom(type: TelehealthWaitTimeResponse): TelehealthWaitTimeModel =
        TelehealthWaitTimeModel(
            beforeTimeout= Duration.ofMinutes(type.beforeTimeout).toKotlinDuration(),
            afterTimeout = Duration.ofMinutes(type.afterTimeout).toKotlinDuration())
}