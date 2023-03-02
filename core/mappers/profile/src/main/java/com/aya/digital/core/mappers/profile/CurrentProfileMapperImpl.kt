package com.aya.digital.core.mappers.profile

import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.data.profile.mappers.CurrentProfileMapper
import com.aya.digital.core.data.profile.mappers.RoleMapper
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

internal class CurrentProfileMapperImpl(private val roleMapper: RoleMapper) :
    CurrentProfileMapper() {
    override fun mapFrom(type: CurrentProfileResponse): CurrentProfile =
        CurrentProfile(
            id = type.id,
            email = type.email,
            firstName = type.firstName,
            lastName = type.lastName,
            middleName = type.middleName,
            ssn = type.ssn,
            sex = type.sex,
            sexAtBirth = type.sexAtBirth,
            dateOfBirth = Clock.System.todayIn(TimeZone.currentSystemDefault()),
            driverLicense = type.driverLicense,
            phoneNumber = type.phoneNumber,
            avatar = type.avatar,
            emergencyContactEnabled = type.emergencyContactEnabled,
            emergencyContactName = type.emergencyContactName,
            emergencyContactPhone = type.emergencyContactPhone,
            roles = roleMapper.mapFromList(type.roles)
        )
}