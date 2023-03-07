package com.aya.digital.core.domain.auth

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.model.RestorePasswordSendCodeModel
import io.reactivex.rxjava3.core.Single

interface RestorePasswordSendCodeUseCase {
    operator fun invoke(model: RestorePasswordSendCodeModel): Single<RequestResultModel<Boolean>>
}

