package com.aya.digital.core.mappers.profile

import com.aya.digital.core.data.profile.mappers.UserKeyResultMapper
import com.aya.digital.core.data.profile.UserKeyResult
import com.aya.digital.core.network.model.response.auth.UserKeyResponse

internal class UserKeyResultMapperImpl : UserKeyResultMapper() {
    override fun mapFrom(type: UserKeyResponse): UserKeyResult =
         UserKeyResult(key = "")
}