package com.aya.digital.core.domain.auth.signin

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.signin.model.SignInResult
import io.reactivex.rxjava3.core.Single
import net.openid.appauth.AuthorizationRequest

interface SignInOAuthUseCase {
    operator fun invoke(): Single<RequestResultModel<AuthorizationRequest>>
}

