package com.aya.digital.core.domain.auth.restorepassword.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.auth.restorepassword.model.RestoreCodeResult
import com.aya.digital.core.domain.auth.restorepassword.RestorePasswordSendCodeGetUserKeyUseCase
import com.aya.digital.core.domain.auth.restorepassword.model.RestorePasswordSendCodeModel
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class RestorePasswordSendCodeUseCaseImpl(
    private val authRepository: AuthRepository,
    private val progressRepository: ProgressRepository
) :
    RestorePasswordSendCodeGetUserKeyUseCase {
    override fun invoke(model: RestorePasswordSendCodeModel): Single<RequestResultModel<RestoreCodeResult>> =
        authRepository.getRestoreToken(model.code)
            .trackProgress(progressRepository)
            .mapResult({ result ->
                if (result.key != null && result.key!!.isNotBlank()) RestoreCodeResult.Success(
                    result.key!!
                ).asResultModel() else RestoreCodeResult.Error.asResultModel()
            }, { it.toModelError() })
}