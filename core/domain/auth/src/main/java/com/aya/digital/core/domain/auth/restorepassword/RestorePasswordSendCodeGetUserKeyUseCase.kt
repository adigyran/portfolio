package com.aya.digital.core.domain.auth.restorepassword

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.restorepassword.model.RestoreCodeResult
import com.aya.digital.core.domain.auth.restorepassword.model.RestorePasswordSendCodeModel
import io.reactivex.rxjava3.core.Single

interface RestorePasswordSendCodeGetUserKeyUseCase {
    operator fun invoke(model: RestorePasswordSendCodeModel): Single<RequestResultModel<RestoreCodeResult>>
}

