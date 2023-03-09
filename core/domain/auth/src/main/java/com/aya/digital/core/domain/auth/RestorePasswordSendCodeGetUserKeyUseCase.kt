package com.aya.digital.core.domain.auth

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.model.RestoreCodeResult
import com.aya.digital.core.domain.auth.model.RestorePasswordSendCodeModel
import com.aya.digital.core.domain.auth.model.VerifyCodeResult
import io.reactivex.rxjava3.core.Single

interface RestorePasswordSendCodeGetUserKeyUseCase {
    operator fun invoke(model: RestorePasswordSendCodeModel): Single<RequestResultModel<RestoreCodeResult>>
}

