package com.aya.digital.core.domain.profile.address.impl

import com.aya.digital.core.data.base.dataprocessing.RequestResultModel
import com.aya.digital.core.data.base.dataprocessing.asResultModel
import com.aya.digital.core.data.base.dataprocessing.toModelError
import com.aya.digital.core.data.profile.LatLng
import com.aya.digital.core.data.profile.repository.AddressRepository
import com.aya.digital.core.data.progress.repository.ProgressRepository
import com.aya.digital.core.domain.base.models.progress.trackProgress
import com.aya.digital.core.domain.profile.address.GeocodeCoordinatesUseCase
import com.aya.digital.core.domain.profile.address.model.PlacePredictionModel
import com.aya.digital.core.domain.profile.address.model.ReverseGeocodingPredictionPlaceModel
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.mapResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

internal class GeocodeCoordinatesUseCaseImpl(
    private val addressRepository: AddressRepository,
    private val progressRepository: ProgressRepository
) : GeocodeCoordinatesUseCase {
    override fun invoke(
        lat: Double,
        lon: Double
    ): Observable<RequestResultModel<List<PlacePredictionModel>>> =
        addressRepository.reverseGeocodeCoordinates(LatLng(lat, lon))
            .trackProgress(progressRepository)
            .mapResult({ predictions ->
                predictions.map {
                    PlacePredictionModel(
                        it.placeId,
                        "",
                        "",
                        it.formattedAddress
                    )
                }.asResultModel()
            }, { it.toModelError() })
}