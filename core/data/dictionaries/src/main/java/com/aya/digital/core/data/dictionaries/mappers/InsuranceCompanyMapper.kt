package com.aya.digital.core.data.dictionaries.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.dictionaries.InsuranceCompanyModel
import com.aya.digital.core.network.model.response.profile.InsuranceCompanyResponse

abstract class InsuranceCompanyMapper :
    EntityMapper<InsuranceCompanyResponse, InsuranceCompanyModel>