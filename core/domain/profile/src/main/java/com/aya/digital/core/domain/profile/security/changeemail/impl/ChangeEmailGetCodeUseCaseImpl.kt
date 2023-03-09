package com.aya.digital.core.domain.profile.security.changeemail.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.domain.profile.security.changeemail.ChangeEmailGetCodeUseCase
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

class ChangeEmailGetCodeUseCaseImpl(
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository
) : ChangeEmailGetCodeUseCase {
    override fun invoke(): Single<RequestResultModel<Boolean>> =
        profileRepository.currentProfile()
            .flatMapResult({ profile ->
                profile.email?.let { sendCode(it) } ?: Single.just(false.asResultModel())
            }, { Single.just(it.toModelError()) })

    private fun sendCode(email: String) = authRepository.changeEmailSendCode(email)
        .mapResult({ it.asResultModel() }, { it.toModelError() })
}