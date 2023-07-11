package com.aya.digital.core.domain.profile.security.changephone.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.security.changephone.ChangePhoneConfirmCodeUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

class ChangePhoneConfirmCodeUseCaseImpl(
    private val profileRepository: ProfileRepository,
    private val progressRepository: ProgressRepository
) : ChangePhoneConfirmCodeUseCase {
    override fun invoke(code: String): Single<RequestResultModel<Boolean>> = profileRepository
        .sendPhoneVerificationCode(code)
        .trackProgress(progressRepository)
        .mapResult({it.asResultModel()},{it.toModelError()})
}