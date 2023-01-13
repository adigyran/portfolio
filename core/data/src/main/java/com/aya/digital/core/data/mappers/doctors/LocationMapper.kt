package com.aya.digital.core.data.mappers.doctors

import com.aya.digital.core.data.model.doctors.Location
import com.aya.digital.core.data.model.profile.CurrentProfile
import com.aya.digital.core.network.model.response.doctors.LocationResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.util.mappers.base.EntityMapper

abstract class LocationMapper : EntityMapper<LocationResponse,Location>