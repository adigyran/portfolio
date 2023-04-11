package com.aya.digital.core.mappers.doctors

import com.aya.digital.core.data.doctors.Clinic
import com.aya.digital.core.data.doctors.Speciality
import com.aya.digital.core.data.doctors.mappers.ClinicMapper
import com.aya.digital.core.data.doctors.mappers.SpecialityMapper
import com.aya.digital.core.network.model.response.doctors.ClinicResponse
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse

internal class ClinicMapperImpl : ClinicMapper() {
    override fun mapFrom(type: ClinicResponse): Clinic =
        Clinic(
            id = type.id,
            name = type.name
        )
}
