package com.aya.digital.core.domain.profile.address

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.address.model.PlacePredictionModel
import io.reactivex.rxjava3.core.Single

interface GetPlacesByAddressQueryUseCase {
    operator fun invoke(address:String): Single<RequestResultModel<List<PlacePredictionModel>>>
}

