package com.aya.digital.core.data.doctors.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.doctors.Practitioners
import com.aya.digital.core.network.model.response.doctors.PractitionersResponse
abstract class PractitionersMapper : EntityMapper<PractitionersResponse, Practitioners>