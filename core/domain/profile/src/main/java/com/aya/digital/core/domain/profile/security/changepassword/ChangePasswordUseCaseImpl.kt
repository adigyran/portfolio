package com.aya.digital.core.domain.profile.security.changepassword

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.security.changepassword.model.ChangePasswordModel
import io.reactivex.rxjava3.core.Single

class ChangePasswordUseCaseImpl : ChangePasswordUseCase {
    override fun invoke(
        code: String,
        changePasswordModel: ChangePasswordModel
    ): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}