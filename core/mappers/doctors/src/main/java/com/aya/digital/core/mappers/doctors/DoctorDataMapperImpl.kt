package com.aya.digital.core.mappers.doctors

import com.aya.digital.core.data.doctors.mappers.DoctorDataMapper
import com.aya.digital.core.data.doctors.mappers.LocationMapper
import com.aya.digital.core.data.doctors.mappers.SpecialityMapper
import com.aya.digital.core.network.model.response.doctors.DoctorDataResponse


internal class DoctorDataMapperImpl(
    private val specialityMapper: SpecialityMapper,
    private val locationMapper: LocationMapper
) : DoctorDataMapper() {
    override fun mapFrom(type: DoctorDataResponse): com.aya.digital.core.data.doctors.DoctorData {
        TODO("Not yet implemented")
    }
}