package com.aya.digital.core.domain.profile.security.changeemail.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.security.changeemail.ChangeEmailUseCase
import io.reactivex.rxjava3.core.Single

internal class ChangeEmailUseCaseImpl : ChangeEmailUseCase {
    override fun invoke(code: String, newEmail: String): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}