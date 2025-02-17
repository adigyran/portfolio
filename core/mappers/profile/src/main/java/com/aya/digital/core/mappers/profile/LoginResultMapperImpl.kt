package com.aya.digital.core.mappers.profile

import com.aya.digital.core.data.mappers.profile.LoginResultMapper
import com.aya.digital.core.data.profile.LoginResult
import com.aya.digital.core.network.model.response.auth.LoginResponse

internal class LoginResultMapperImpl : LoginResultMapper() {
    override fun mapFrom(type: LoginResponse): LoginResult =
        LoginResult(type.token ?: "", type.refreshToken ?: "")
}