package com.aya.digital.core.data.profile.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.profile.LoginResult
import com.aya.digital.core.data.profile.UserKeyResult
import com.aya.digital.core.network.model.response.auth.LoginResponse
import com.aya.digital.core.network.model.response.auth.UserKeyResponse

abstract class UserKeyResultMapper :
    EntityMapper<UserKeyResponse, UserKeyResult>