package com.aya.digital.core.data.doctors.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.doctors.Location
import com.aya.digital.core.network.model.response.doctors.LocationResponse

abstract class LocationMapper : EntityMapper<LocationResponse, Location>