package com.aya.digital.core.mappers.profile

import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.data.profile.mappers.RoleMapper
import com.aya.digital.core.network.model.response.profile.RoleResponse

internal class RoleMapperImpl : RoleMapper() {
    override fun mapFrom(type: RoleResponse): CurrentProfile.Role =
        CurrentProfile.Role(
            id = type.id,
            name = type.name
        )
}