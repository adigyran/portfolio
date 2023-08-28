package com.aya.digital.core.mappers.doctors

import com.aya.digital.core.data.doctors.DoctorData
import com.aya.digital.core.data.doctors.PatientData
import com.aya.digital.core.data.doctors.mappers.*
import com.aya.digital.core.network.model.response.doctors.DoctorDataResponse
import com.aya.digital.core.network.model.response.patient.PatientDataResponse
import com.aya.digital.core.util.datetime.DateTimeUtils
import kotlinx.datetime.toJavaLocalDate


internal class PatientDataMapperImpl(
    private val insuranceMapper: InsuranceMapper,
    private val dateTimeUtils: DateTimeUtils
) : PatientDataMapper() {
    override fun mapFrom(type: PatientDataResponse): PatientData  =
        PatientData(
            id = type.id,
            firstName = type.firstName,
            lastName = type.lastName,
            middleName = type.middleName,
            avatarPhotoLink = type.photo,
            birthDate = type.birthDate?.let(dateTimeUtils::parseIsoDate)?.toJavaLocalDate(),
            insurances = type.insurances?.let { insuranceMapper.mapFromList(it) }?: listOf()
        )
}