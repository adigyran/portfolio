package com.aya.digital.core.mappers.profile

import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.data.profile.mappers.AvatarMapper
import com.aya.digital.core.data.profile.mappers.CurrentProfileMapper
import com.aya.digital.core.data.profile.mappers.RoleMapper
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.util.datetime.DateTimeUtils
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

internal class CurrentProfileMapperImpl(private val avatarMapper: AvatarMapper,private val dateTimeUtils: DateTimeUtils) :
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
            tin = type.tin,
            jwtUserUuid = type.jwtUserUuid,
            weight = type.weight,
            height = type.height,
            dateOfBirth = type.dateOfBirth?.let(dateTimeUtils::parseIsoDate),
            avatar = type.avatar?.let { avatarMapper.mapFrom(it) },
            shortAddress = type.address
        )
}