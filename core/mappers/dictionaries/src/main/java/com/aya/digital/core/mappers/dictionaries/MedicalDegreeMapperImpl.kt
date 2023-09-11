package com.aya.digital.core.mappers.dictionaries

import com.aya.digital.core.data.dictionaries.InsuranceCompanyModel
import com.aya.digital.core.data.dictionaries.MedicalDegreeModel
import com.aya.digital.core.data.dictionaries.SpecialityModel
import com.aya.digital.core.data.dictionaries.mappers.InsuranceCompanyMapper
import com.aya.digital.core.data.dictionaries.mappers.MedicalDegreeMapper
import com.aya.digital.core.data.dictionaries.mappers.SpecialityMapper
import com.aya.digital.core.network.model.request.InsuranceBody
import com.aya.digital.core.network.model.response.doctors.MedicalDegreeResponse
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse
import com.aya.digital.core.network.model.response.profile.InsuranceCompanyResponse


internal class MedicalDegreeMapperImpl : MedicalDegreeMapper() {
    override fun mapFrom(type: MedicalDegreeResponse): MedicalDegreeModel = MedicalDegreeModel(
        id = type.id,
        name = type.name,
        code = type.code
    )

}