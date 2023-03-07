package com.aya.digital.core.domain.auth.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.RestorePasswordChangePasswordUseCase
import com.aya.digital.core.domain.auth.model.RestorePasswordChangePasswordModel
import io.reactivex.rxjava3.core.Single

internal class RestorePasswordChangePasswordUseCaseImpl : RestorePasswordChangePasswordUseCase {
    override fun invoke(model: RestorePasswordChangePasswordModel): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}