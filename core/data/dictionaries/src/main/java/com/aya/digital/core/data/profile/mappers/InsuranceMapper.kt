package com.aya.digital.core.data.profile.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.profile.InsuranceModel
import com.aya.digital.core.network.model.request.InsuranceBody
import com.aya.digital.core.network.model.response.profile.RoleResponse

abstract class InsuranceMapper :
    EntityMapper<InsuranceBody, InsuranceModel>