package com.aya.digital.core.data.profile

data class LatLng(val lat:Double, val lon:Double)

fun LatLng.inTextForm() = "%f,%f".format(lat,lon)