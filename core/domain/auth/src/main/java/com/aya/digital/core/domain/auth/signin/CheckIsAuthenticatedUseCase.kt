package com.aya.digital.core.domain.auth.signin

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single

interface CheckIsAuthenticatedUseCase {
    operator fun invoke(): Single<RequestResultModel<Boolean>>
}

