package com.aya.digital.core.data.mappers.profile

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.profile.InsurancePolicyModel
import com.aya.digital.core.network.model.response.profile.InsurancePolicyResponse

abstract class InsurancePolicyMapper :
    EntityMapper<InsurancePolicyResponse, InsurancePolicyModel>