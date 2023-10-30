package com.aya.digital.core.domain.profile.address.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.address.GetProfileAddressUseCase
import com.aya.digital.core.domain.profile.address.SaveProfileAddressUseCase
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class SaveProfileAddressUseCaseImpl(
    private val profileRepository: ProfileRepository,
    private val progressRepository: ProgressRepository
) : SaveProfileAddressUseCase {
    override fun invoke(addressLine:String): Single<RequestResultModel<Boolean>> =
        profileRepository.saveAddress(addressLine)
            .trackProgress(progressRepository)
            .mapResult({it.asResultModel()},
                { it.toModelError() })
}