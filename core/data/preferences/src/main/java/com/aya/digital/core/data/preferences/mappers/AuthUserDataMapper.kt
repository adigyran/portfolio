package com.aya.digital.core.data.preferences.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.preferences.AuthUserData
import com.aya.digital.core.network.model.auth.Tokens

abstract class AuthUserDataMapper : EntityMapper<Tokens, AuthUserData>