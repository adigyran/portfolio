package com.aya.digital.core.domain.profile

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetProfileUseCaseImpl(val profileRepository: ProfileRepository) :
    GetProfileBriefUseCase {
    override fun invoke(): Single<RequestResultModel<BriefProfileModel>> =
        profileRepository.currentProfile()
            .mapResult({ it.mapToBriefProfile().asResultModel() }, { it.toModelError() })

}