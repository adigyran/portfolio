package com.aya.digital.core.mappers.profile.practitioner

import com.aya.digital.core.data.profile.mappers.practitioner.PractitionerProfileMapper
import com.aya.digital.core.data.profile.practitioner.PractitionerProfile
import com.aya.digital.core.network.model.response.doctor.PractitionerProfileResponse

internal class PractitionerProfileMapperImpl : PractitionerProfileMapper() {
    override fun mapFrom(type: PractitionerProfileResponse): PractitionerProfile {
        TODO("Not yet implemented")
    }
}