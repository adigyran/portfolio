package com.aya.digital.core.mappers.dictionaries

import com.aya.digital.core.data.profile.InsuranceModel
import com.aya.digital.core.data.profile.mappers.InsuranceMapper
import com.aya.digital.core.network.model.request.InsuranceBody
import com.aya.digital.core.network.model.response.profile.RoleResponse


internal class InsuranceMapperImpl : InsuranceMapper() {
    override fun mapFrom(type: InsuranceBody): InsuranceModel =
        InsuranceModel(
            id = type.id,
            name = type.nameOrg?:""
        )
}