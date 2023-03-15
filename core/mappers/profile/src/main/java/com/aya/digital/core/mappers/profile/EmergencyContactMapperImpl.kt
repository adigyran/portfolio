package com.aya.digital.core.mappers.profile

import com.aya.digital.core.data.profile.EmergencyContact
import com.aya.digital.core.data.profile.mappers.EmergencyContactMapper
import com.aya.digital.core.network.model.response.EmergencyContactResponse

internal class EmergencyContactMapperImpl : EmergencyContactMapper() {
    override fun mapFrom(type: EmergencyContactResponse): EmergencyContact =
        EmergencyContact(type.name,type.phone)
}