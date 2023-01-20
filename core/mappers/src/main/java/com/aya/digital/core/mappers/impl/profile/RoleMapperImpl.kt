package com.aya.digital.core.mappers.impl.profile

import com.aya.digital.core.data.mappers.profile.RoleMapper
import com.aya.digital.core.data.model.profile.CurrentProfile
import com.aya.digital.core.network.model.response.profile.RoleResponse

class RoleMapperImpl : RoleMapper() {
    override fun mapFrom(type: RoleResponse): CurrentProfile.Role =
        CurrentProfile.Role(
            id = type.id,
            name = type.name
        )
}