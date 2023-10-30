package com.aya.digital.core.network.model.response.geocoding

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReverseGeocodingPredictionItemResponse(
   @Json(name = "addressComponents")
   val addressComponents: List<AddressComponent>?,
   @Json(name = "formattedAddress")
   val formattedAddress: String?,
   @Json(name = "postcodeLocalities")
   val postcodeLocalities: Any?,
   @Json(name = "geometry")
   val geometry: Geometry?,
   @Json(name = "types")
   val types: List<String>?,
   @Json(name = "partialMatch")
   val partialMatch: Boolean?,
   @Json(name = "placeId")
   val placeId: String?,
   @Json(name = "plusCode")
   val plusCode: PlusCode?
){
   @JsonClass(generateAdapter = true)
   data class AddressComponent(
      @Json(name = "longName")
      val longName: String?,
      @Json(name = "shortName")
      val shortName: String?,
      @Json(name = "types")
      val types: List<String>?
   )

   @JsonClass(generateAdapter = true)
   data class Geometry(
      @Json(name = "bounds")
      val bounds: Bounds?,
      @Json(name = "location")
      val location: Location?,
      @Json(name = "locationType")
      val locationType: String?,
      @Json(name = "viewport")
      val viewport: Viewport?
   ) {
      @JsonClass(generateAdapter = true)
      data class Bounds(
         @Json(name = "northeast")
         val northeast: Northeast?,
         @Json(name = "southwest")
         val southwest: Southwest?
      ) {
         @JsonClass(generateAdapter = true)
         data class Northeast(
            @Json(name = "lat")
            val lat: Double?,
            @Json(name = "lng")
            val lng: Double?
         )

         @JsonClass(generateAdapter = true)
         data class Southwest(
            @Json(name = "lat")
            val lat: Double?,
            @Json(name = "lng")
            val lng: Double?
         )
      }

      @JsonClass(generateAdapter = true)
      data class Location(
         @Json(name = "lat")
         val lat: Double?,
         @Json(name = "lng")
         val lng: Double?
      )

      @JsonClass(generateAdapter = true)
      data class Viewport(
         @Json(name = "northeast")
         val northeast: Northeast?,
         @Json(name = "southwest")
         val southwest: Southwest?
      ) {
         @JsonClass(generateAdapter = true)
         data class Northeast(
            @Json(name = "lat")
            val lat: Double?,
            @Json(name = "lng")
            val lng: Double?
         )

         @JsonClass(generateAdapter = true)
         data class Southwest(
            @Json(name = "lat")
            val lat: Double?,
            @Json(name = "lng")
            val lng: Double?
         )
      }
   }

   @JsonClass(generateAdapter = true)
   data class PlusCode(
      @Json(name = "globalCode")
      val globalCode: String?,
      @Json(name = "compoundCode")
      val compoundCode: String?
   )
}