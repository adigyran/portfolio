package com.aya.digital.core.domain.auth

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single

internal class SignInUseCaseImpl(val authRepository: AuthRepository) : SignInUseCase {

    override fun invoke(
        email: String,
        password: String,
    ): Single<RequestResultModel<SignInResult>> = authRepository.generateToken(email, password)
        .flatMapResult({ authRepository.saveToken(it.token, it.refreshToken) }, { Single.just(it) })
        .mapResult({ SignInResult.asResultModel() }, {
            it.toModelError()
        })
}