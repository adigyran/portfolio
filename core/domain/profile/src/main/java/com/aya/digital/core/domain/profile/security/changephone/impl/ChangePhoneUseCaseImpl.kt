package com.aya.digital.core.domain.profile.security.changephone.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.security.changephone.ChangePhoneUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

class ChangePhoneUseCaseImpl(
    private val profileRepository: ProfileRepository,
    private val progressRepository: ProgressRepository
) : ChangePhoneUseCase {
    override fun invoke(newPhone: String): Single<RequestResultModel<Boolean>> = profileRepository
        .updatePhoneNumber(newPhone)
        .trackProgress(progressRepository)
        .mapResult({it.asResultModel()},{it.toModelError()})
}