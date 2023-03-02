package com.aya.digital.core.domain.profile

import com.aya.digital.core.networkbase.server.RequestResult
import com.aya.digital.core.repository.profile.ProfileRepository

class GetProfileUseCaseImpl(val profileRepository: ProfileRepository) : GetProfileUseCase {
    override fun invoke(email: String, password: String): RequestResult<SignInResult> {
        TODO("Not yet implemented")
    }
}