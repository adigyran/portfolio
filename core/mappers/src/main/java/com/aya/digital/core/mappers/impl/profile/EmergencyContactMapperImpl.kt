package com.aya.digital.core.mappers.impl.profile

import com.aya.digital.core.data.mappers.profile.EmergencyContactMapper
import com.aya.digital.core.data.model.profile.EmergencyContact
import com.aya.digital.core.network.model.response.EmergencyContactResponse

internal class EmergencyContactMapperImpl : EmergencyContactMapper() {
    override fun mapFrom(type: EmergencyContactResponse): EmergencyContact {
        TODO("Not yet implemented")
    }
}