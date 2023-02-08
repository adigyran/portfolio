package com.aya.digital.core.data.profile.practitioner

import kotlinx.datetime.LocalDate

data class PractitionerProfile(
    val id: Int,
    val firstName: String?,
    val middleName: String?,
    val lastName: String?,
    val specialtyId: Int?,
    val specialtyName: String?,
    val specialityCode: String?,
    val dateOfBirth: LocalDate?,
    val sex: String?,
    val phoneNumber: String?,
    val emailAddress: String?,
    val addressLine1: String?,
    val addressLine2: String?,
    val city: String?,
    val state: String?,
    val zip: String?,
    val photo: String?,
    val rating: Int?,
    val about: String?,
    val numberOfUpcomingAppointments: String?,
    val latitude: Int?,
    val longitude: Int?,
)