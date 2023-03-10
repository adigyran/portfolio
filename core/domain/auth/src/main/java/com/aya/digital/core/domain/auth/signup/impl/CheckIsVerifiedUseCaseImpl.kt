package com.aya.digital.core.domain.auth.signup.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.domain.auth.signup.CheckIsVerifiedUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class CheckIsVerifiedUseCaseImpl(private val authRepository: AuthRepository) : CheckIsVerifiedUseCase {
    override fun invoke(email: String): Single<RequestResultModel<Boolean>> =
        authRepository.checkIsVerified(email)
            .mapResult({it.asResultModel()},{it.toModelError()})
}