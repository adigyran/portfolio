package com.aya.digital.core.domain.auth

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class CheckIsAuthenticatedUseCaseImpl(private val authRepository: AuthRepository) : CheckIsAuthenticatedUseCase {
    override fun invoke(): Single<RequestResultModel<Boolean>> =
        authRepository.checkIfTokenPresent()
            .mapResult({it.asResultModel()},{it.toModelError()})
}