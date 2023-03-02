package com.aya.digital.core.domain.profile

import com.aya.digital.core.networkbase.server.RequestResult

interface GetProfileUseCase {
    operator fun invoke(email: String, password: String): RequestResult<SignInResult>
}

data class AuthResult(
    val id: Int,
    val email: String,
    val token: String,
)

object SignInResult