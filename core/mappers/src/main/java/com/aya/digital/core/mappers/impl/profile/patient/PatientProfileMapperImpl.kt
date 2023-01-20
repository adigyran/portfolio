package com.aya.digital.core.mappers.impl.profile.patient

import com.aya.digital.core.data.mappers.profile.patient.PatientProfileMapper
import com.aya.digital.core.data.model.profile.patient.PatientProfile
import com.aya.digital.core.network.model.response.patient.PatientProfileResponse

class PatientProfileMapperImpl : PatientProfileMapper() {
    override fun mapFrom(type: PatientProfileResponse): PatientProfile {
        TODO("Not yet implemented")
    }
}