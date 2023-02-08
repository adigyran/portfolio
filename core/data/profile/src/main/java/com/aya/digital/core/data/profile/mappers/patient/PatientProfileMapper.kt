package com.aya.digital.core.data.profile.mappers.patient

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.profile.patient.PatientProfile
import com.aya.digital.core.network.model.response.patient.PatientProfileResponse

abstract class PatientProfileMapper : EntityMapper<PatientProfileResponse, PatientProfile>