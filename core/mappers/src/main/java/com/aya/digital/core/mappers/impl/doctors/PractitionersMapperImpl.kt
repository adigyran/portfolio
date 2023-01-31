package com.aya.digital.core.mappers.impl.doctors

import com.aya.digital.core.data.mappers.doctors.DoctorDataMapper
import com.aya.digital.core.data.mappers.doctors.PractitionersMapper
import com.aya.digital.core.data.model.doctors.Practitioners
import com.aya.digital.core.network.model.response.doctors.PractitionersResponse

internal class PractitionersMapperImpl(private val doctorDataMapper: DoctorDataMapper) : PractitionersMapper() {
    override fun mapFrom(type: PractitionersResponse): Practitioners {
        TODO("Not yet implemented")
    }
}