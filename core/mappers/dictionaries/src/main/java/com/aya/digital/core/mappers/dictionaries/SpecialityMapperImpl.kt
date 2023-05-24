package com.aya.digital.core.mappers.dictionaries

import com.aya.digital.core.data.dictionaries.InsuranceCompanyModel
import com.aya.digital.core.data.dictionaries.SpecialityModel
import com.aya.digital.core.data.dictionaries.mappers.InsuranceCompanyMapper
import com.aya.digital.core.data.dictionaries.mappers.SpecialityMapper
import com.aya.digital.core.network.model.request.InsuranceBody
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse
import com.aya.digital.core.network.model.response.profile.InsuranceCompanyResponse


internal class SpecialityMapperImpl : SpecialityMapper() {
    override fun mapFrom(type: SpecialityResponse): SpecialityModel = SpecialityModel(
        id = type.id,
        name = type.name,
        code = type.code
    )

}