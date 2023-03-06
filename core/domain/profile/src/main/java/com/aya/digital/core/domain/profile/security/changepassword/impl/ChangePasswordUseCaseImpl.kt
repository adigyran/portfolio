package com.aya.digital.core.domain.profile.security.changepassword.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.security.changepassword.ChangePasswordUseCase
import com.aya.digital.core.domain.profile.security.changepassword.model.ChangePasswordModel
import io.reactivex.rxjava3.core.Single

internal class ChangePasswordUseCaseImpl : ChangePasswordUseCase {
    override fun invoke(
        code: String,
        changePasswordModel: ChangePasswordModel
    ): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}