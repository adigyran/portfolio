package com.aya.digital.core.domain.profile.generalinfo.view.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileBriefUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.model.BriefProfileModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.mapToBriefProfile
import com.aya.digital.core.ext.flatMapResult
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetProfileUseCaseImpl(
    private val profileRepository: ProfileRepository,
    private val progressRepository: ProgressRepository
) :
    GetProfileBriefUseCase {
    override fun invoke(): Single<RequestResultModel<BriefProfileModel>> =
        profileRepository.currentProfile()
            .trackProgress(progressRepository)
            .flatMapResult({ profile ->
                profileRepository.currentProfileAvatar()
                    .mapResult({ avatar ->
                        profile.mapToBriefProfile(avatar).asResultModel()
                    }, { it.toModelError() })
            }, { Single.just(it.toModelError()) })

}