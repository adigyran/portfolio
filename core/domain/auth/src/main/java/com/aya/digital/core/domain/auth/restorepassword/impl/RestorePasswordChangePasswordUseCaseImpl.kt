package com.aya.digital.core.domain.auth.restorepassword.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.domain.auth.restorepassword.RestorePasswordChangePasswordUseCase
import com.aya.digital.core.domain.auth.restorepassword.model.RestorePasswordChangePasswordModel
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.network.model.request.ResetPasswordBody
import io.reactivex.rxjava3.core.Single

internal class RestorePasswordChangePasswordUseCaseImpl(private val authRepository: AuthRepository) :
    RestorePasswordChangePasswordUseCase {
    override fun invoke(
        model: RestorePasswordChangePasswordModel
    ): Single<RequestResultModel<Boolean>> =
        authRepository.restorePassword(
            token = model.token,
            resetPasswordBody = ResetPasswordBody(
                password = model.newPassword,
                confirmPassword = model.newPasswordDuplicate
            )
        )
            .mapResult({ it.asResultModel() }, { it.toModelError() })
}