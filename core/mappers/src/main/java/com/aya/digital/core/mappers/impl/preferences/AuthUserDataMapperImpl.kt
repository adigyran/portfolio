package com.aya.digital.core.mappers.impl.preferences

import com.aya.digital.core.data.mappers.preferences.AuthUserDataMapper
import com.aya.digital.core.data.model.preferences.AuthUserData
import com.aya.digital.core.network.model.auth.Tokens

internal class AuthUserDataMapperImpl : AuthUserDataMapper() {
    override fun mapFrom(type: Tokens): AuthUserData =
        AuthUserData(
            accessToken = type.accessToken,
            idToken = type.idToken,
            refreshToken = type.refreshToken
        )
}