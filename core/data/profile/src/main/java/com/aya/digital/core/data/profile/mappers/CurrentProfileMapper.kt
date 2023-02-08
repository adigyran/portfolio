package com.aya.digital.core.data.profile.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse

abstract class CurrentProfileMapper : EntityMapper<CurrentProfileResponse, CurrentProfile>