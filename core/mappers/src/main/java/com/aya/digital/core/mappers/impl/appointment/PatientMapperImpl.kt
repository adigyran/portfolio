package com.aya.digital.core.mappers.impl.appointment

import com.aya.digital.core.data.mappers.appointment.PatientMapper
import com.aya.digital.core.data.model.appointment.AppoinmentSlot
import com.aya.digital.core.network.model.response.SlotResponse

class PatientMapperImpl : PatientMapper() {
    override fun mapFrom(type: SlotResponse): AppoinmentSlot {
        TODO("Not yet implemented")
    }
}