package com.aya.digital.core.domain.profile.address.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.repository.AddressRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.address.GetPlaceByIdUseCase
import com.aya.digital.core.domain.profile.address.model.PlaceModel
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Single

internal class GetPlaceByIdUseCaseImpl(
    private val addressRepository: AddressRepository,
    private val progressRepository: ProgressRepository
) : GetPlaceByIdUseCase {
    override fun invoke(placeId: String): Single<RequestResultModel<PlaceModel>> =
        addressRepository.getPlaceById(placeId)
            .trackProgress(progressRepository)
            .mapResult({ placeModel ->
                PlaceModel(
                    id = placeModel.id,
                    addressText = placeModel.addressText,
                    location = placeModel.location?.let {
                        PlaceModel.PlaceLocation(
                            it.lat,
                            it.lon
                        )
                    }).asResultModel()
            }, { it.toModelError() })
}