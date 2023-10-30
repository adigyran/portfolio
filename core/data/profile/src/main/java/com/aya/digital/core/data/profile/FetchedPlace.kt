package com.aya.digital.core.data.profile

data class FetchedPlace(val id: String?, val addressText:String?, val location: PlaceLocation?)
{
    data class PlaceLocation(val lat:Double,val lon:Double)
}