package com.aya.digital.core.mappers.impl.preferences

import com.aya.digital.core.data.mappers.preferences.AuthUserDataMapper
import com.aya.digital.core.data.mappers.profile.CurrentProfileMapper
import com.aya.digital.core.data.mappers.profile.MessageMapper
import com.aya.digital.core.data.mappers.profile.RoleMapper
import com.aya.digital.core.data.model.preferences.AuthUserData
import com.aya.digital.core.data.model.profile.CurrentProfile
import com.aya.digital.core.data.model.profile.Message
import com.aya.digital.core.network.model.auth.Tokens
import com.aya.digital.core.network.model.response.MessageResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.network.model.response.profile.RoleResponse

class AuthUserDataMapperImpl : AuthUserDataMapper() {
    override fun mapFrom(type: Tokens): AuthUserData =
        AuthUserData(
            accessToken = type.accessToken,
            idToken = type.idToken,
            refreshToken = type.refreshToken
        )
}