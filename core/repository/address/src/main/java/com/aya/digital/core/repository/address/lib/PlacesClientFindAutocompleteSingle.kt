package com.aya.digital.core.repository.address.lib

import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
import com.google.android.libraries.places.api.net.PlacesClient
import io.reactivex.rxjava3.core.Single

public fun PlacesClient.findAutocompletePredictionRx(
    actions: FindAutocompletePredictionsRequest.Builder.() -> Unit
): Single<FindAutocompletePredictionsResponse> =
    PlacesClientFindAutocompletePredictionsSingle(this, actions)

private class PlacesClientFindAutocompletePredictionsSingle(
    private val placesClient: PlacesClient,
    private val actions: FindAutocompletePredictionsRequest.Builder.() -> Unit
) : MainThreadTaskSingle<FindAutocompletePredictionsResponse>() {
    override fun invokeRequest(listener: TaskCompletionListener<FindAutocompletePredictionsResponse>) {
        val request = FindAutocompletePredictionsRequest.builder()
            .apply(actions)
            .setCancellationToken(listener.cancellationTokenSource.token)
            .build()
        placesClient.findAutocompletePredictions(request).addOnCompleteListener(listener)
    }
}