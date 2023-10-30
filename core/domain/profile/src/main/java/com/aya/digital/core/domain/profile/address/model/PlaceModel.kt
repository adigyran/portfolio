package com.aya.digital.core.domain.profile.address.model

data class PlaceModel(val id: String?,val addressText:String?, val location:PlaceLocation?)
{
    data class PlaceLocation(val lat:Double,val lon:Double)
}
