package com.aya.digital.core.data.profile.repository

import com.aya.digital.core.data.profile.FetchedPlace
import com.aya.digital.core.data.profile.LatLng
import com.aya.digital.core.data.profile.PredictionPlace
import com.aya.digital.core.data.profile.ReverseGeocodingPredictionItem
import com.aya.digital.core.networkbase.server.RequestResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface AddressRepository {

    fun initAutocompleteSession():Boolean

    fun getPlaceById(placeId:String): Single<RequestResult<FetchedPlace>>

    fun getPlacesPredictionsAddresses(textQuery: String): Single<RequestResult<List<PredictionPlace>>>
    fun reverseGeocodeCoordinates(latLng: LatLng): Observable<RequestResult<List<ReverseGeocodingPredictionItem>>>
}