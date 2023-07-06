package com.aya.digital.core.domain.profile.security.changepassword.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AuthRepository
import com.aya.digital.core.data.profile.repository.AuthTokenRepository
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.security.changepassword.ChangePasswordUseCase
import com.aya.digital.core.domain.profile.security.changepassword.model.ChangePasswordModel
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.network.model.request.ChangePasswordBody
import com.aya.digital.core.network.model.request.ResetPasswordBody
import io.reactivex.rxjava3.core.Single

internal class ChangePasswordUseCaseImpl(
    private val authRepository: AuthRepository,
    private val authTokenRepository: AuthTokenRepository,
    private val progressRepository: ProgressRepository
) : ChangePasswordUseCase {
    override fun invoke(
        changePasswordModel: ChangePasswordModel
    ): Single<RequestResultModel<Boolean>> = authRepository.changePassword(
        ChangePasswordBody(
            changePasswordModel.currentPassword,
            changePasswordModel.newPassword,
            changePasswordModel.newPasswordRepeat
        )
    )
        .trackProgress(progressRepository)
        .flatMapResult({ passwordChanged ->
            if (passwordChanged) updateToken() else Single.just(false.asResult())
        }, { Single.just(it) })
        .mapResult({ it.asResultModel() }, { it.toModelError() })

    private fun updateToken() = authTokenRepository.refreshAndSaveTokens()
}