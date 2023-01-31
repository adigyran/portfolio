package com.aya.digital.core.data.mappers.profile

import com.aya.digital.core.data.model.profile.LoginResult
import com.aya.digital.core.network.model.response.profile.LoginResponse
import com.aya.digital.core.util.mappers.base.EntityMapper

abstract class LoginResultMapper : EntityMapper<LoginResponse, LoginResult>