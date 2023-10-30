package com.aya.digital.core.network.api.services

import com.aya.digital.core.network.model.request.*
import com.aya.digital.core.network.model.response.geocoding.ReverseGeocodingPredictionItemResponse
import com.aya.digital.core.network.model.response.profile.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface GeocodingApiService {
    @GET("maps/api/geocode/json")
    fun reverseGeocodingByCoordinates(@Query("latlng") latLng: String,@Query("result_type") resultType:String) : Observable<List<ReverseGeocodingPredictionItemResponse>>
}