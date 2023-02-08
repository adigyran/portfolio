package com.aya.digital.core.mappers.profile.patient

import com.aya.digital.core.data.profile.mappers.patient.PatientProfileMapper
import com.aya.digital.core.data.profile.patient.PatientProfile
import com.aya.digital.core.network.model.response.patient.PatientProfileResponse

internal class PatientProfileMapperImpl : PatientProfileMapper() {
    override fun mapFrom(type: PatientProfileResponse): PatientProfile {
        TODO("Not yet implemented")
    }
}