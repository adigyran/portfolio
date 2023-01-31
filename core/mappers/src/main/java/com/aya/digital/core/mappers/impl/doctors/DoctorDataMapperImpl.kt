package com.aya.digital.core.mappers.impl.doctors

import com.aya.digital.core.data.mappers.doctors.DoctorDataMapper
import com.aya.digital.core.data.mappers.doctors.LocationMapper
import com.aya.digital.core.data.mappers.doctors.SpecialityMapper
import com.aya.digital.core.data.model.doctors.DoctorData
import com.aya.digital.core.network.model.response.doctors.DoctorDataResponse

internal class DoctorDataMapperImpl(private val specialityMapper: SpecialityMapper, private val locationMapper: LocationMapper) : DoctorDataMapper() {
    override fun mapFrom(type: DoctorDataResponse): DoctorData {
        TODO("Not yet implemented")
    }
}