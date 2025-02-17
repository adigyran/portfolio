package com.aya.digital.core.domain.auth.signin

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.signin.model.SignInResult
import io.reactivex.rxjava3.core.Single

interface SignInUseCase {
    operator fun invoke(email: String, password: String): Single<RequestResultModel<SignInResult>>
}

