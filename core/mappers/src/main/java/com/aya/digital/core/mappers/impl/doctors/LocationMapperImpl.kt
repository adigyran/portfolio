package com.aya.digital.core.mappers.impl.doctors

import com.aya.digital.core.data.mappers.doctors.LocationMapper
import com.aya.digital.core.data.model.doctors.Location
import com.aya.digital.core.network.model.response.doctors.LocationResponse

internal class LocationMapperImpl : LocationMapper() {
    override fun mapFrom(type: LocationResponse): Location {
        TODO("Not yet implemented")
    }
}