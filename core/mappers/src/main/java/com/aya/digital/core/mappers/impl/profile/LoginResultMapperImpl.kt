package com.aya.digital.core.mappers.impl.profile

import com.aya.digital.core.data.mappers.profile.LoginResultMapper
import com.aya.digital.core.data.model.profile.LoginResult
import com.aya.digital.core.network.model.response.profile.LoginResponse

class LoginResultMapperImpl : LoginResultMapper() {
    override fun mapFrom(type: LoginResponse): LoginResult {
        TODO("Not yet implemented")
    }
}