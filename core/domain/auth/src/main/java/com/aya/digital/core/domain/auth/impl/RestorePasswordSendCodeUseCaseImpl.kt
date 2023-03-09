package com.aya.digital.core.domain.auth.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.domain.auth.RestorePasswordSendCodeGetUserKeyUseCase
import com.aya.digital.core.domain.auth.model.RestoreCodeResult
import com.aya.digital.core.domain.auth.model.RestorePasswordSendCodeModel
import com.aya.digital.core.domain.auth.model.VerifyCodeResult
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class RestorePasswordSendCodeUseCaseImpl(private val authRepository: AuthRepository) :
    RestorePasswordSendCodeGetUserKeyUseCase {
    override fun invoke(model: RestorePasswordSendCodeModel): Single<RequestResultModel<RestoreCodeResult>> =
        authRepository.getRestoreToken(model.code)
            .mapResult({ result ->
                if (result.key != null && result.key!!.isNotBlank()) RestoreCodeResult.Success(
                    result.key!!
                ).asResultModel() else RestoreCodeResult.Error.asResultModel()
            }, { it.toModelError() })
}