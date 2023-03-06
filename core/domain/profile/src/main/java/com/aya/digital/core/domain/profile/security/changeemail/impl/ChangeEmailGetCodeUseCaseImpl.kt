package com.aya.digital.core.domain.profile.security.changeemail.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.security.changeemail.ChangeEmailGetCodeUseCase
import io.reactivex.rxjava3.core.Single

class ChangeEmailGetCodeUseCaseImpl : ChangeEmailGetCodeUseCase {
    override fun invoke(): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}