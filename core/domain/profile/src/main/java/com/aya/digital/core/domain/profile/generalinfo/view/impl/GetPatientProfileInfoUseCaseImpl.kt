package com.aya.digital.core.domain.profile.generalinfo.view.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.flatMapResult
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileBriefUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.GetProfileInfoUseCase
import com.aya.digital.core.domain.profile.generalinfo.view.model.ProfileInfoModel
import com.aya.digital.core.domain.profile.generalinfo.view.model.mapToPatientProfile
import com.aya.digital.core.domain.profile.generalinfo.view.model.mapToProfileInfo
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetPatientProfileInfoUseCaseImpl(
    private val profileRepository: ProfileRepository,
    private val getProfileBriefUseCase: GetProfileBriefUseCase,
    private val progressRepository: ProgressRepository
) :
    GetProfileInfoUseCase {
    override fun invoke(): Single<RequestResultModel<ProfileInfoModel>> =
        getProfileBriefUseCase()
            .trackProgress(progressRepository)
            .flatMapResult({ briefProfile ->
                profileRepository.currentProfile()
                    .mapResult({ profile ->
                        briefProfile.mapToProfileInfo()
                            .apply { flavoredProfileModel = profile.mapToPatientProfile() }
                            .asResultModel()
                    }, { it.toModelError() })
            }, { Single.just(it) })
}