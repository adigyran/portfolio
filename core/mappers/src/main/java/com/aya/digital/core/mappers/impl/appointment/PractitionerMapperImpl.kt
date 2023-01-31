package com.aya.digital.core.mappers.impl.appointment

import com.aya.digital.core.data.mappers.appointment.PractitionerMapper
import com.aya.digital.core.data.model.appointment.Practitioner
import com.aya.digital.core.network.model.response.PractitionerResponse

internal class PractitionerMapperImpl : PractitionerMapper() {
    override fun mapFrom(type: PractitionerResponse): Practitioner {
        TODO("Not yet implemented")
    }
}