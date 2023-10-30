package com.aya.digital.core.repository.address.impl

import android.content.Context
import com.aya.digital.core.data.profile.FetchedPlace
import com.aya.digital.core.data.profile.LatLng
import com.aya.digital.core.data.profile.PredictionPlace
import com.aya.digital.core.data.profile.ReverseGeocodingPredictionItem
import com.aya.digital.core.data.profile.inTextForm
import com.aya.digital.core.data.profile.mappers.ReverseGeocodingPredictionItemMapper
import com.aya.digital.core.data.profile.repository.AddressRepository
import com.aya.digital.core.datasource.GeocodingApiDataSource
import com.aya.digital.core.ext.asResult
import com.aya.digital.core.ext.mapResult
import com.aya.digital.core.ext.retrofitResponseToResult
import com.aya.digital.core.ext.retryOnError
import com.aya.digital.core.networkbase.CommonUtils
import com.aya.digital.core.networkbase.server.RequestResult
import com.aya.digital.core.repository.address.lib.fetchPlace
import com.aya.digital.core.repository.address.lib.findAutocompletePredictionRx
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.PlaceTypes
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import timber.log.Timber

internal class AddressRepositoryImpl(
    private val context: Context,
    private val geocodingApiDataSource: GeocodingApiDataSource,
    private val reverseGeocodingPredictionItemMapper: ReverseGeocodingPredictionItemMapper
) : AddressRepository {
    private lateinit var placesClient: PlacesClient
    private var sessionToken: AutocompleteSessionToken? = null

    override fun initAutocompleteSession(): Boolean {
        placesClient = Places.createClient(context)
        sessionToken = AutocompleteSessionToken.newInstance()
        return true
    }

    override fun getPlaceById(placeId: String) =
        fetchPlace(
            placeId = placeId,
            placeFields = listOf(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.ADDRESS,
                Place.Field.LAT_LNG,
                Place.Field.VIEWPORT
            )
        )
            .map { placeResponse ->
                with(placeResponse.place)
                {
                    FetchedPlace(
                        id = id,
                        addressText = address,
                        location = latLng?.let {
                            FetchedPlace.PlaceLocation(
                                it.latitude,
                                it.longitude
                            )
                        }).asResult()
                }
            }

    override fun reverseGeocodeCoordinates(latLng: LatLng): Observable<RequestResult<List<ReverseGeocodingPredictionItem>>> =
        geocodingApiDataSource.reverseGeocodingByCoordinates(latLng.inTextForm())
            .retryOnError()
            .retrofitResponseToResult(CommonUtils::mapServerErrors)
            .mapResult({ predictions ->
                predictions.filter { it.placeId != null }
                    .map { reverseGeocodingPredictionItemMapper.mapFrom(it) }.asResult()
            }, { it })

    private fun getPlacesPredictions(textQuery: String) =
        placesClient.findAutocompletePredictionRx {
            sessionToken = sessionToken
            typesFilter = listOf(PlaceTypes.ADDRESS)
            query = textQuery
        }
            .map {
                it.autocompletePredictions.asResult()
            }

    private fun fetchPlace(placeId: String, placeFields: List<Place.Field>) =
        placesClient.fetchPlace(
            placeId = placeId,
            placeFields = placeFields
        )


    override fun getPlacesPredictionsAddresses(textQuery: String): Single<RequestResult<List<PredictionPlace>>> =
        getPlacesPredictions(textQuery)
            .mapResult({ placesPredictions ->
                placesPredictions.map { place ->
                    PredictionPlace(
                        place.placeId,
                        place.getPrimaryText(null),
                        place.getSecondaryText(null)
                    )
                }.asResult()
            }, { it })


    private fun String.createRequest() = FindAutocompletePredictionsRequest.builder()
        .setTypesFilter(listOf(PlaceTypes.ADDRESS))
        .setSessionToken(sessionToken)
        .setQuery(this)
        .build()

}