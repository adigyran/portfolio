package com.aya.digital.core.domain.auth.restorepassword.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.auth.restorepassword.RestorePasswordGetCodeUseCase
import com.aya.digital.core.domain.auth.restorepassword.model.RestorePasswordGetCodeModel
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class RestorePasswordGetCodeUseCaseImpl(
    private val authRepository: AuthRepository,
    private val progressRepository: ProgressRepository
) :
    RestorePasswordGetCodeUseCase {
    override fun invoke(model: RestorePasswordGetCodeModel): Single<RequestResultModel<Boolean>> =
        authRepository.getRestoreCode(model.email)
            .trackProgress(progressRepository)
            .mapResult({ it.asResultModel() }, { it.toModelError() })

}