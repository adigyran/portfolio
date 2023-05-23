package com.aya.digital.core.mappers.appointment

import com.aya.digital.core.data.appointment.mappers.PractitionerMapper
import com.aya.digital.core.data.appointment.Practitioner
import com.aya.digital.core.network.model.response.PractitionerResponse

internal class PractitionerMapperImpl : PractitionerMapper() {
    override fun mapFrom(type: PractitionerResponse): Practitioner {
        TODO("Not yet implemented")
    }
}