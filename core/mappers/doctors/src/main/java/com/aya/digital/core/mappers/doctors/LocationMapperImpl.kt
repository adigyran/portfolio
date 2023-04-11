package com.aya.digital.core.mappers.doctors

import com.aya.digital.core.data.doctors.Location
import com.aya.digital.core.data.doctors.mappers.LocationMapper
import com.aya.digital.core.network.model.response.doctors.LocationResponse

internal class LocationMapperImpl : LocationMapper() {
    override fun mapFrom(type: LocationResponse): Location =
        Location(latitude = type.latitude, longitude = type.longitude)
}