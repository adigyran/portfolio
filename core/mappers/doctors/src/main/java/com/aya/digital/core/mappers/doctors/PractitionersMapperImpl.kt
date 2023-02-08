package com.aya.digital.core.mappers.doctors

import com.aya.digital.core.data.doctors.Practitioners
import com.aya.digital.core.data.doctors.mappers.DoctorDataMapper
import com.aya.digital.core.data.doctors.mappers.PractitionersMapper
import com.aya.digital.core.network.model.response.doctors.PractitionersResponse

internal class PractitionersMapperImpl(private val doctorDataMapper: DoctorDataMapper) :
    PractitionersMapper() {
    override fun mapFrom(type: PractitionersResponse): Practitioners {
        TODO("Not yet implemented")
    }
}