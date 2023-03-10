package com.aya.digital.core.domain.auth.restorepassword.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.restorepassword.MakeNewPasswordUseCase
import com.aya.digital.core.domain.auth.restorepassword.model.MakeNewPasswordModel
import io.reactivex.rxjava3.core.Single

internal class MakeNewPasswordUseCaseImpl : MakeNewPasswordUseCase {
    override fun invoke(model: MakeNewPasswordModel): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}