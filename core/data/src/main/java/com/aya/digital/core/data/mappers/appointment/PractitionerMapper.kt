package com.aya.digital.core.data.mappers.appointment

import com.aya.digital.core.data.model.appointment.Practitioner
import com.aya.digital.core.network.model.response.PractitionerResponse
import com.aya.digital.core.util.mappers.base.EntityMapper

abstract class PractitionerMapper : EntityMapper<PractitionerResponse, Practitioner>