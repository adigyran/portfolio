package com.aya.digital.core.domain.auth.signup.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.domain.auth.signup.SignUpGetCodeUseCase
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class SignUpGetCodeUseCaseImpl(private val authRepository: AuthRepository) : SignUpGetCodeUseCase {
    override fun invoke(email: String): Single<RequestResultModel<Boolean>> =
        authRepository.getVerificationCode(email)
            .mapResult({it.asResultModel()},{it.toModelError()})
}