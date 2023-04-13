package com.aya.digital.core.mappers.doctors

import com.aya.digital.core.data.doctors.Speciality
import com.aya.digital.core.data.doctors.mappers.SpecialityMapper
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse

internal class SpecialityMapperImpl : SpecialityMapper() {
    override fun mapFrom(type: SpecialityResponse): Speciality =
        Speciality(
            id = type.id,
            specialtyCode = type.code,
            specialtyName = type.name
        )
}