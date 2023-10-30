package com.aya.digital.core.data.profile

data class ReverseGeocodingPredictionItem(
    val addressComponents: List<AddressComponent>?,
    val formattedAddress: String,
    val types: List<String>?,
    val partialMatch: Boolean?,
    val placeId: String,
){
    data class AddressComponent(
        val longName: String?,
        val shortName: String?,
        val types: List<String?>?
    )
}