package com.aya.digital.core.domain.auth.restorepassword

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.restorepassword.model.RestorePasswordGetCodeModel
import io.reactivex.rxjava3.core.Single

interface RestorePasswordGetCodeUseCase {
    operator fun invoke(model: RestorePasswordGetCodeModel): Single<RequestResultModel<Boolean>>
}