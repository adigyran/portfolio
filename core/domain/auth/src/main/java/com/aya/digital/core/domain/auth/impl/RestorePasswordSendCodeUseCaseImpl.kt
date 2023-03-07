package com.aya.digital.core.domain.auth.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.RestorePasswordSendCodeUseCase
import com.aya.digital.core.domain.auth.model.RestorePasswordSendCodeModel
import com.aya.digital.core.domain.auth.model.VerifyCodeResult
import io.reactivex.rxjava3.core.Single

internal class RestorePasswordSendCodeUseCaseImpl : RestorePasswordSendCodeUseCase {
    override fun invoke(model: RestorePasswordSendCodeModel): Single<RequestResultModel<VerifyCodeResult>> {
        TODO("Not yet implemented")
    }
}