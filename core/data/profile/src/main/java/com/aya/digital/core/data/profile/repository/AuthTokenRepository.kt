package com.aya.digital.core.data.profile.repository

import com.aya.digital.core.data.profile.LoginResult
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single

interface AuthTokenRepository {

    fun refreshAndSaveTokens(refreshToken: String): Single<RequestResult<Boolean>>
}