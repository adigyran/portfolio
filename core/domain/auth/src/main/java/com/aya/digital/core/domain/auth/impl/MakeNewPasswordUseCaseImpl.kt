package com.aya.digital.core.domain.auth.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.MakeNewPasswordUseCase
import com.aya.digital.core.domain.auth.model.MakeNewPasswordModel
import io.reactivex.rxjava3.core.Single

class MakeNewPasswordUseCaseImpl : MakeNewPasswordUseCase {
    override fun invoke(model: MakeNewPasswordModel): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}