package com.aya.digital.core.network.model.request

import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate

@JsonClass(generateAdapter = true)
data class PatientProfileBody(
    val id: Int? = null,
    val patientId: String? = null,
    val firstName: String? = null,
    val middleName: String? = null,
    val lastName: String? = null,
    val dateOfBirth: LocalDate? = null,
    val ssn: String? = null,
    val driverLicense: String? = null,
    val sex: String? = null,
    val phoneNumber: String? = null,
    val mobilePhoneNumber: String? = null,
    val emailAddress: String? = null,
    val addressLine1: String? = null,
    val addressLine2: String? = null,
    val city: String? = null,
    val state: String? = null,
    val stateId: Int? = null,
    val zip: String? = null,
    val emergencyContactName: String? = null,
    val emergencyContactPhone: String? = null,
    val emergencyContactEnabled: Boolean? = null,
    val pharmacyName: String? = null,
    val pharmacyPhoneNumber: String? = null,
    val nokLastName: String? = null,
    val nokFirstName: String? = null,
    val nokRelationship: String? = null,
    val nokPhoneNumber: String? = null,
    val nokAddressLine1: String? = null,
    val nokEmail: String? = null,
    val insPlanName: String? = null,
    val insPhoneNumber: String? = null,
    val insGroupId: String? = null,
    val insMemberId: String? = null,
    val latitude: Int? = null,
    val longitude: Int? = null,
)
