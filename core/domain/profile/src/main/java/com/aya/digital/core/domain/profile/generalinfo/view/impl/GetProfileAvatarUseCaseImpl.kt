package com.aya.digital.core.domain.profile.generalinfo.view.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileAvatarUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetProfileAvatarUseCaseImpl(
    private val profileRepository: ProfileRepository,
    private val progressRepository: ProgressRepository
) : GetProfileAvatarUseCase {
    override fun invoke(): Single<RequestResultModel<String?>> =
        profileRepository.currentProfileAvatar()
            .trackProgress(progressRepository)
            .mapResult({ it?.fullUrl.asResultModel() }, { it.toModelError() })
}