package com.aya.digital.core.data.mappers.profile.patient

import com.aya.digital.core.data.model.profile.CurrentProfile
import com.aya.digital.core.data.model.profile.patient.PatientProfile
import com.aya.digital.core.network.model.response.patient.PatientProfileResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.util.mappers.base.EntityMapper

abstract class PatientProfileMapper : EntityMapper<PatientProfileResponse,PatientProfile>