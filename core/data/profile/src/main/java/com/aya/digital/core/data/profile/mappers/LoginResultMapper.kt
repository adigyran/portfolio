package com.aya.digital.core.data.mappers.profile

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.profile.LoginResult
import com.aya.digital.core.network.model.response.auth.LoginResponse

abstract class LoginResultMapper :
    EntityMapper<LoginResponse, LoginResult>