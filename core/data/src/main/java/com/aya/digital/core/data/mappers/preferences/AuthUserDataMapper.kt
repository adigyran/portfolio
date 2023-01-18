package com.aya.digital.core.data.mappers.preferences

import com.aya.digital.core.data.model.preferences.AuthUserData
import com.aya.digital.core.network.model.auth.Tokens
import com.aya.digital.core.util.mappers.base.EntityMapper

abstract class AuthUserDataMapper : EntityMapper<Tokens, AuthUserData>