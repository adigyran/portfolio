package com.aya.digital.core.domain.auth

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.model.RestorePasswordChangePasswordModel
import io.reactivex.rxjava3.core.Single

interface RestorePasswordChangePasswordUseCase {
    operator fun invoke(model: RestorePasswordChangePasswordModel): Single<RequestResultModel<Boolean>>
}