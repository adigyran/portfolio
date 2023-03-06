package com.aya.digital.core.domain.profile.security.changeemail

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import io.reactivex.rxjava3.core.Single

class ChangeEmailUseCaseImpl : ChangeEmailUseCase {
    override fun invoke(code: String, newEmail: String): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}