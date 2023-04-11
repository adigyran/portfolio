package com.aya.digital.core.data.doctors.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.doctors.Insurance
import com.aya.digital.core.network.model.response.doctors.InsuranceResponse

abstract class InsuranceMapper : EntityMapper<InsuranceResponse, Insurance>