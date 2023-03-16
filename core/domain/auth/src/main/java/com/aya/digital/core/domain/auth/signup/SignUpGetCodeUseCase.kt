package com.aya.digital.core.domain.auth.signup

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.auth.restorepassword.model.RestorePasswordGetCodeModel
import io.reactivex.rxjava3.core.Single

interface SignUpGetCodeUseCase {
    operator fun invoke(email:String): Single<RequestResultModel<Boolean>>
}