package com.aya.digital.core.repository.address.lib

import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import com.google.android.libraries.places.api.net.PlacesClient
import io.reactivex.rxjava3.core.Single

public fun PlacesClient.fetchPlace(
    placeId: String,
    placeFields: List<Place.Field>,
    actions: FetchPlaceRequest.Builder.() -> Unit = {}
): Single<FetchPlaceResponse> =
    PlacesClientFetchPlaceSingle(
        placesClient = this,
        placeId = placeId,
        placeFields = placeFields,
        actions = actions
    )

private class PlacesClientFetchPlaceSingle(
    private val placesClient: PlacesClient,
    private val placeId: String,
    private val placeFields: List<Place.Field>,
    private val actions: FetchPlaceRequest.Builder.() -> Unit
) : MainThreadTaskSingle<FetchPlaceResponse>() {
    override fun invokeRequest(listener: TaskCompletionListener<FetchPlaceResponse>) {
        val request = FetchPlaceRequest.builder(placeId, placeFields)
            .apply(actions)
            .setCancellationToken(listener.cancellationTokenSource.token)
            .build()
        placesClient.fetchPlace(request).addOnCompleteListener(listener)
    }
}