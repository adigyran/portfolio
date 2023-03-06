package com.aya.digital.core.domain.profile.security.changepassword.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.security.changepassword.ChangePasswordGetCodeUseCase
import io.reactivex.rxjava3.core.Single

internal class ChangePasswordGetCodeUseCaseImpl : ChangePasswordGetCodeUseCase {
    override fun invoke(): Single<RequestResultModel<Boolean>> {
        TODO("Not yet implemented")
    }
}