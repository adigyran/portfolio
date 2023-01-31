package com.aya.digital.core.data.mappers.profile.practitioner

import com.aya.digital.core.data.model.profile.practitioner.PractitionerProfile
import com.aya.digital.core.network.model.response.doctor.PractitionerProfileResponse
import com.aya.digital.core.util.mappers.base.EntityMapper

abstract class PractitionerProfileMapper : EntityMapper<PractitionerProfileResponse,PractitionerProfile>