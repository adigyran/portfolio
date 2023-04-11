package com.aya.digital.core.mappers.doctors

import com.aya.digital.core.data.doctors.Insurance
import com.aya.digital.core.data.doctors.Speciality
import com.aya.digital.core.data.doctors.mappers.InsuranceMapper
import com.aya.digital.core.data.doctors.mappers.SpecialityMapper
import com.aya.digital.core.network.model.response.doctors.InsuranceResponse
import com.aya.digital.core.network.model.response.doctors.SpecialityResponse

internal class InsuranceMapperImpl : InsuranceMapper() {
    override fun mapFrom(type: InsuranceResponse): Insurance =
        Insurance(
            id = type.id,
            name = type.name
        )

}