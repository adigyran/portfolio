package com.aya.digital.core.mappers.profile

import com.aya.digital.core.data.mappers.profile.InsurancePolicyMapper
import com.aya.digital.core.data.profile.InsurancePolicyModel
import com.aya.digital.core.network.model.response.profile.InsuranceCompanyResponse
import com.aya.digital.core.network.model.response.profile.InsurancePolicyResponse

class InsurancePolicyMapperImpl : InsurancePolicyMapper() {
    override fun mapFrom(type: InsurancePolicyResponse): InsurancePolicyModel =
        InsurancePolicyModel(
            id = type.id,
            organisationId = type.organizationId,
            organisationName = type.organizationName,
            number = type.number,
            photoUrl = type.fullUrl
        )
}