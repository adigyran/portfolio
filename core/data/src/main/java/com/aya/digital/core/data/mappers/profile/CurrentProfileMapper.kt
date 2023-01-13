package com.aya.digital.core.data.mappers.profile

import com.aya.digital.core.data.model.profile.CurrentProfile
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.util.mappers.base.EntityMapper

abstract class CurrentProfileMapper : EntityMapper<CurrentProfileResponse,CurrentProfile>