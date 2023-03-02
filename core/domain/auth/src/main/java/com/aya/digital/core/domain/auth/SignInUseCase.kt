package com.aya.digital.core.domain.auth

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single

interface SignInUseCase {
    operator fun invoke(email: String, password: String): Single<RequestResultModel<SignInResult>>
}

data class AuthResult(
    val id: Int,
    val email: String,
    val token: String,
)

object SignInResult