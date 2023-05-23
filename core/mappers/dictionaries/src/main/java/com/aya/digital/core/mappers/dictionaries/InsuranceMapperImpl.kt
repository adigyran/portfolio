package com.aya.digital.core.mappers.dictionaries

import com.aya.digital.core.data.dictionaries.InsuranceCompanyModel
import com.aya.digital.core.data.dictionaries.mappers.InsuranceCompanyMapper
import com.aya.digital.core.network.model.request.InsuranceBody
import com.aya.digital.core.network.model.response.profile.InsuranceCompanyResponse


internal class InsuranceCompanyMapperImpl : InsuranceCompanyMapper() {
    override fun mapFrom(type: InsuranceCompanyResponse): InsuranceCompanyModel =
        InsuranceCompanyModel(
            id = type.id,
            name = type.name?:""
        )
}