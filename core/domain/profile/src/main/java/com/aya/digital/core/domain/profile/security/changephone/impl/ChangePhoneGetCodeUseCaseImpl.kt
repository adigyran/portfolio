package com.aya.digital.core.domain.profile.security.changephone.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.security.changephone.ChangePhoneGetCodeUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

class ChangePhoneGetCodeUseCaseImpl(
    private val profileRepository: ProfileRepository,
    private val progressRepository: ProgressRepository
) : ChangePhoneGetCodeUseCase {
    override fun invoke(): Single<RequestResultModel<Boolean>> = profileRepository
        .getPhoneVerificationCode()
        .trackProgress(progressRepository)
        .mapResult({it.asResultModel()},{it.toModelError()})
}