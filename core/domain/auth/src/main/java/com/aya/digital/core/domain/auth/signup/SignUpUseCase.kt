package com.aya.digital.core.domain.auth.signup

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.signup.model.SignUpResult
import io.reactivex.rxjava3.core.Single

interface SignUpUseCase {
    operator fun invoke(
        email: String,
        firstName: String,
        lastName: String,
        insurances: List<Int>,
        password: String,
        passwordConfirm: String,
    ): Single<RequestResultModel<SignUpResult>>
}
