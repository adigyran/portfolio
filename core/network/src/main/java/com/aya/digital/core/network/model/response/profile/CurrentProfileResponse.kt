package com.aya.digital.core.network.model.response.profile

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate

@JsonClass(generateAdapter = true)
data class CurrentProfileResponse(
    val id: Int,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val middleName: String?,
    val ssn: String?,
    val sex: String?,
    val sexAtBirth: String?,
    @Json( name = "birthDate") val dateOfBirth: LocalDate?,
    val driverLicense: String?,
    val phoneNumber: String?,
    @Json( name = "avatarPath") val avatar: String?,
    val emergencyContactEnabled: Boolean,
    val emergencyContactName: String?,
    val emergencyContactPhone: String?,
    @Json( name = "fhirRoles") val roles: List<RoleResponse>,
) {

}

@JsonClass(generateAdapter = true)
data class RoleResponse(
    val id: Int,
    val name: String
)