package com.aya.digital.core.mappers.preferences

import com.aya.digital.core.data.preferences.AuthUserData
import com.aya.digital.core.data.preferences.mappers.AuthUserDataMapper
import com.aya.digital.core.network.model.auth.Tokens

internal class AuthUserDataMapperImpl : AuthUserDataMapper() {
    override fun mapFrom(type: Tokens): AuthUserData =
        AuthUserData(
            accessToken = type.accessToken,
            refreshToken = type.refreshToken
        )
}