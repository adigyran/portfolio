package com.aya.digital.core.domain.profile.address

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.domain.profile.address.model.PlacePredictionModel
import com.aya.digital.core.domain.profile.address.model.ReverseGeocodingPredictionPlaceModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface GeocodeCoordinatesUseCase {
    operator fun invoke(lat:Double,lon:Double): Observable<RequestResultModel<List<PlacePredictionModel>>>
}

