package com.aya.digital.core.domain.auth

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.model.MakeNewPasswordModel
import com.aya.digital.core.domain.auth.model.RestorePasswordSendCodeModel
import io.reactivex.rxjava3.core.Single

interface MakeNewPasswordUseCase {
    operator fun invoke(model: MakeNewPasswordModel): Single<RequestResultModel<Boolean>>
}

