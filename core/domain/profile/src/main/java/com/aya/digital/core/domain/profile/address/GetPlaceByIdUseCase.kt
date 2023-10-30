package com.aya.digital.core.domain.profile.address

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.address.model.PlaceModel
import io.reactivex.rxjava3.core.Single

interface GetPlaceByIdUseCase {
    operator fun invoke(placeId:String): Single<RequestResultModel<PlaceModel>>
}

