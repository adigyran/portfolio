package com.aya.digital.core.domain.auth.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.RestorePasswordGetCodeUseCase
import com.aya.digital.core.domain.auth.model.RestorePasswordGetCodeModel
import io.reactivex.rxjava3.core.Single

internal class RestorePasswordGetCodeUseCaseImpl : RestorePasswordGetCodeUseCase {
    override fun invoke(model: RestorePasswordGetCodeModel): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}