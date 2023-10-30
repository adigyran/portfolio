package com.aya.digital.core.domain.profile.address.model

data class ProfileAddressModel(val addressLine: String, val location:ProfileAddressLatLon?)
{
    data class ProfileAddressLatLon(val lat: Double, val lon: Double)
}