package com.aya.digital.core.mappers.doctors

import com.aya.digital.core.data.doctors.DoctorData
import com.aya.digital.core.data.doctors.mappers.*
import com.aya.digital.core.network.model.response.doctors.DoctorDataResponse


internal class DoctorDataMapperImpl(
    private val specialityMapper: SpecialityMapper,
    private val locationMapper: LocationMapper,
    private val insuranceMapper: InsuranceMapper,
    private val clinicMapper: ClinicMapper
) : DoctorDataMapper() {
    override fun mapFrom(type: DoctorDataResponse): DoctorData  =
        DoctorData(
            id = type.id,
            firstName = type.firstName,
            lastName = type.lastName,
            middleName = type.middleName,
            avatarPhotoLink = type.photo,
            bio = type.bio?:"",
            city = type.city?:"",
            address = type.address?:"",
            postalCode = type.postalCode?:"",
            specialities = type.specialities?.let { specialityMapper.mapFromList(it) }?: listOf(),
            insurances = type.insurances?.let { insuranceMapper.mapFromList(it) }?: listOf(),
            clinics = type.clinics?.let { clinicMapper.mapFromList(it) }?: listOf(),
            location = type.location?.let { locationMapper.mapFrom(it) }
        )
}