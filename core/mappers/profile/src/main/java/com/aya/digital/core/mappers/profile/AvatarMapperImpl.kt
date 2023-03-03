package com.aya.digital.core.mappers.profile

import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.data.profile.mappers.AvatarMapper
import com.aya.digital.core.data.profile.mappers.RoleMapper
import com.aya.digital.core.network.model.response.profile.AvatarResponse
import com.aya.digital.core.network.model.response.profile.RoleResponse

internal class AvatarMapperImpl : AvatarMapper() {
    override fun mapFrom(type: AvatarResponse): CurrentProfile.Avatar =
        CurrentProfile.Avatar(
            contentType = type.contentType,
            fullUrl = type.fullUrl,
            originName = type.originName,
            shortUrl = type.shortUrl
        )

}