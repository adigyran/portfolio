package com.aya.digital.core.domain.profile.security.changeemail

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import io.reactivex.rxjava3.core.Single

interface ChangeEmailGetCodeUseCase {
    operator fun invoke(): Single<RequestResultModel<Boolean>>
}

