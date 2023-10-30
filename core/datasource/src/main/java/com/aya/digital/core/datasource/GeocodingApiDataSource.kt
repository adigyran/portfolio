package com.aya.digital.core.datasource

import com.aya.digital.core.network.model.response.geocoding.ReverseGeocodingPredictionItemResponse
import io.reactivex.rxjava3.core.Observable

interface GeocodingApiDataSource {
    fun reverseGeocodingByCoordinates(latLng: String):Observable<List<ReverseGeocodingPredictionItemResponse>>
}