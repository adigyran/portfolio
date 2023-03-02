package com.aya.digital.core.domain.auth

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal  class SignUpUseCaseImpl(val authRepository: AuthRepository) : SignUpUseCase {
    override fun invoke(
        email: String,
        firstName: String,
        lastName: String,
        insurances: List<Int>,
        password: String,
        passwordConfirm: String
    ): Single<RequestResultModel<SignUpResult>>  =
        authRepository.registerProfile(email, firstName, lastName, insurances, password, passwordConfirm)
            .mapResult({SignUpResult.asResultModel()},{it.toModelError()})
}