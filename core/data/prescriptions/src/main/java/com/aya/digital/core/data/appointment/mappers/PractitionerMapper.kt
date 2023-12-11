package com.aya.digital.core.data.appointment.mappers

import com.aya.digital.core.data.appointment.Practitioner
import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.network.model.response.PractitionerResponse

abstract class PractitionerMapper : EntityMapper<PractitionerResponse, Practitioner>