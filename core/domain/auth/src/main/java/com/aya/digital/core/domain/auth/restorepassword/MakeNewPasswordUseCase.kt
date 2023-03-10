package com.aya.digital.core.domain.auth.restorepassword

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.restorepassword.model.MakeNewPasswordModel
import io.reactivex.rxjava3.core.Single

interface MakeNewPasswordUseCase {
    operator fun invoke(model: MakeNewPasswordModel): Single<RequestResultModel<Boolean>>
}

