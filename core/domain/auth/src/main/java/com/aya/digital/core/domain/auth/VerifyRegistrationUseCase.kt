package com.aya.digital.core.domain.auth

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Single

interface VerifyRegistrationUseCase {
    operator fun invoke(code:String): Single<RequestResultModel<VerifyCodeResult>>
}


sealed class VerifyCodeResult {
    object Success : VerifyCodeResult()
    object Error : VerifyCodeResult()
}