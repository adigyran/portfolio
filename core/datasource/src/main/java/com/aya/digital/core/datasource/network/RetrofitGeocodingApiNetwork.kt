package com.aya.digital.core.datasource.network

import com.aya.digital.core.datasource.*
import com.aya.digital.core.network.api.services.GeocodingApiService
import com.aya.digital.core.network.main.di.modules.createApiService
import com.aya.digital.core.network.model.response.geocoding.ReverseGeocodingPredictionItemResponse
import io.reactivex.rxjava3.core.Observable
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton


fun geoCodingApiNetwork() = DI.Module("geoCodingApiNetwork") {
    bind<GeocodingApiDataSource>() with singleton {
        val apiService =
            createApiService<GeocodingApiService>(instance())
        val googleKey = instance<String>("appGoogleKey")
        return@singleton RetrofitGeocodingApiNetwork(apiService, googleKey)
    }
}

class RetrofitGeocodingApiNetwork(
    private val network: GeocodingApiService,
    private val googleKey: String
) :
    GeocodingApiDataSource {
    override fun reverseGeocodingByCoordinates(
        latLng: String
    ): Observable<List<ReverseGeocodingPredictionItemResponse>> = network.reverseGeocodingByCoordinates(
       latLng = latLng,
        resultType = "street_address"
    )


}
