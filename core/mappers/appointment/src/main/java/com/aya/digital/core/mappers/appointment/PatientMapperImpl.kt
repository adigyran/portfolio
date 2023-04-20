package com.aya.digital.core.mappers.appointment

import com.aya.digital.core.data.appointment.AppoinmentSlot
import com.aya.digital.core.data.appointment.mappers.PatientMapper
import com.aya.digital.core.network.model.response.schedule.SlotResponse

internal class PatientMapperImpl : PatientMapper() {
    override fun mapFrom(type: SlotResponse): AppoinmentSlot {
        TODO("Not yet implemented")
    }
}