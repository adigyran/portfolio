package com.aya.digital.core.mappers.impl.profile

import com.aya.digital.core.data.mappers.profile.CurrentProfileMapper
import com.aya.digital.core.data.mappers.profile.RoleMapper
import com.aya.digital.core.data.model.profile.CurrentProfile
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse

internal class CurrentProfileMapperImpl(private val roleMapper: RoleMapper) : CurrentProfileMapper() {
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
            dateOfBirth = type.dateOfBirth,
            driverLicense = type.driverLicense,
            phoneNumber = type.phoneNumber,
            avatar = type.avatar,
            emergencyContactEnabled = type.emergencyContactEnabled,
            emergencyContactName = type.emergencyContactName,
            emergencyContactPhone = type.emergencyContactPhone,
            roles = roleMapper.mapFromList(type.roles)
        )
}