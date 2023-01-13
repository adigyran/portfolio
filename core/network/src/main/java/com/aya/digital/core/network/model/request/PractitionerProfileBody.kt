package com.aya.digital.core.network.model.request

import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate

@JsonClass(generateAdapter = true)
data class PractitionerProfileBody(
    val id: Int? = null,
    val firstName: String? = null,
    val middleName: String? = null,
    val lastName: String? = null,
    val specialtyId: Int? = null,
    val specialtyName: String? = null,
    val specialityCode: String? = null,
    val dateOfBirth: LocalDate? = null,
    val sex: String? = null,
    val phoneNumber: String? = null,
    val emailAddress: String? = null,
    val addressLine1: String? = null,
    val addressLine2: String? = null,
    val city: String? = null,
    val state: String? = null,
    val stateId: Int? = null,
    val zip: String? = null,
    val photo: String? = null,
    val rating: Int? = null,
    val about: String? = null,
    val numberOfUpcomingAppointments: String? = null,
    val latitude: Int? = null,
    val longitude: Int? = null,
)
