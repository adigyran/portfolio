package com.aya.digital.core.repository.address.lib

import androidx.annotation.RequiresPermission
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse
import com.google.android.libraries.places.api.net.PlacesClient
import io.reactivex.rxjava3.core.Single

@RequiresPermission(allOf = ["android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_WIFI_STATE"])
public fun PlacesClient.findCurrentPlace(
    placeFields: List<Place.Field>
): Single<FindCurrentPlaceResponse> =
    PlacesClientFindCurrentPlaceSingle(
        placesClient = this,
        placeFields = placeFields,
    )

private class PlacesClientFindCurrentPlaceSingle(
    private val placesClient: PlacesClient,
    private val placeFields: List<Place.Field>
) : MainThreadTaskSingle<FindCurrentPlaceResponse>() {
    @RequiresPermission(allOf = ["android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_WIFI_STATE"])
    override fun invokeRequest(listener: TaskCompletionListener<FindCurrentPlaceResponse>) {
        val request = FindCurrentPlaceRequest.builder(placeFields)
            .setCancellationToken(listener.cancellationTokenSource.token)
            .build()
        placesClient.findCurrentPlace(request).addOnCompleteListener(listener)
    }
}