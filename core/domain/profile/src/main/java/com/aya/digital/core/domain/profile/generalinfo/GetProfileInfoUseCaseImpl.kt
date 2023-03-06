package com.aya.digital.core.domain.profile.generalinfo

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetProfileInfoUseCaseImpl(val profileRepository: ProfileRepository) :
    GetProfileInfoUseCase {
    override fun invoke(): Single<RequestResultModel<ProfileInfoModel>> =
        profileRepository.currentProfile()
            .mapResult({it.mapToProfileInfo().asResultModel()},{it.toModelError()})
}