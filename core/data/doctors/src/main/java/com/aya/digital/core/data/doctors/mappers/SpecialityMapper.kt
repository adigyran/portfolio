package com.aya.digital.core.data.doctors.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.doctors.Speciality
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse

abstract class SpecialityMapper : EntityMapper<SpecialityResponse, Speciality>