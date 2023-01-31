package com.aya.digital.core.data.mappers.doctors

import com.aya.digital.core.data.model.doctors.Practitioners
import com.aya.digital.core.network.model.response.doctors.PractitionersResponse
import com.aya.digital.core.util.mappers.base.EntityMapper

abstract class PractitionersMapper : EntityMapper<PractitionersResponse,Practitioners>