package com.aya.digital.core.mappers.dictionaries

import com.aya.digital.core.data.dictionaries.InsuranceCompanyModel
import com.aya.digital.core.data.dictionaries.LanguageModel
import com.aya.digital.core.data.dictionaries.SpecialityModel
import com.aya.digital.core.data.dictionaries.mappers.InsuranceCompanyMapper
import com.aya.digital.core.data.dictionaries.mappers.LanguageMapper
import com.aya.digital.core.data.dictionaries.mappers.SpecialityMapper
import com.aya.digital.core.network.model.request.InsuranceBody
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse
import com.aya.digital.core.network.model.response.language.LanguageResponse
import com.aya.digital.core.network.model.response.profile.InsuranceCompanyResponse


internal class LanguageMapperImpl : LanguageMapper() {

    override fun mapFrom(type: LanguageResponse): LanguageModel = LanguageModel(
        id = type.id, code = type.code, name = type.name
    )
}