package com.aya.digital.core.data.profile.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.network.model.response.profile.AvatarResponse
import com.aya.digital.core.network.model.response.profile.RoleResponse

abstract class AvatarMapper :
    EntityMapper<AvatarResponse, CurrentProfile.Avatar>