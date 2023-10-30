package com.aya.digital.core.domain.profile.address.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.ProfileRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.address.GetProfileAddressUseCase
import com.aya.digital.core.domain.profile.address.model.ProfileAddressModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetProfileAddressUseCaseImpl(
    private val profileRepository: ProfileRepository,
    private val progressRepository: ProgressRepository
) : GetProfileAddressUseCase {
    override fun invoke(): Single<RequestResultModel<ProfileAddressModel>> =
        profileRepository.getAddress()
            .trackProgress(progressRepository)
            .mapResult({ address ->
                val location =
                    if (address.lat != null && address.lon != null) ProfileAddressModel.ProfileAddressLatLon(
                        address.lat!!,
                        address.lon!!
                    ) else null
                ProfileAddressModel(
                    addressLine = address.addressLine ?: "",
                    location = location
                ).asResultModel()
            },
                { it.toModelError() })
}