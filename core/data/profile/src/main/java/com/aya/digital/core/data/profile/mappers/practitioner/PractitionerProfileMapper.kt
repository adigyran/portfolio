package com.aya.digital.core.data.profile.mappers.practitioner

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.profile.practitioner.PractitionerProfile
import com.aya.digital.core.network.model.response.doctor.PractitionerProfileResponse

abstract class PractitionerProfileMapper :
    EntityMapper<PractitionerProfileResponse, PractitionerProfile>