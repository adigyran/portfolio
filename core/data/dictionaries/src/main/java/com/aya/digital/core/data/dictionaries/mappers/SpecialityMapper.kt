package com.aya.digital.core.data.dictionaries.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.dictionaries.InsuranceCompanyModel
import com.aya.digital.core.data.dictionaries.SpecialityModel
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse
import com.aya.digital.core.network.model.response.profile.InsuranceCompanyResponse

abstract class SpecialityMapper :
    EntityMapper<SpecialityResponse, SpecialityModel>