package com.aya.digital.core.data.mappers.doctors

import com.aya.digital.core.data.model.doctors.Speciality
import com.aya.digital.core.data.model.profile.CurrentProfile
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse
import com.aya.digital.core.util.mappers.base.EntityMapper

abstract class SpecialityMapper : EntityMapper<SpecialityResponse, Speciality>