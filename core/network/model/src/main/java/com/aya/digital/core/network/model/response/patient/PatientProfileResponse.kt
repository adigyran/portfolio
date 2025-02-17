package com.aya.digital.core.network.model.response.patient

import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate


@JsonClass(generateAdapter = true)
data class PatientProfileResponse(
    val id: Int,
    val patientId: String?,
    val firstName: String?,
    val middleName: String?,
    val lastName: String?,
    val dateOfBirth: LocalDate?,
    val ssn: String?,
    val sex: String?,
    val phoneNumber: String?,
    val mobilePhoneNumber: String?,
    val emailAddress: String?,
    val addressLine1: String?,
    val addressLine2: String?,
    val city: String?,
    val state: String?,
    val zip: String?,
    val emergencyContactName: String?,
    val emergencyContactPhone: String?,
    val emergencyContactEnabled: Boolean?,
    val pharmacyName: String?,
    val pharmacyPhoneNumber: String?,
    val nokLastName: String?,
    val nokFirstName: String?,
    val nokRelationship: String?,
    val nokPhoneNumber: String?,
    val nokAddressLine1: String?,
    val nokEmail: String?,
    val insPlanName: String?,
    val insPhoneNumber: String?,
    val insGroupId: String?,
    val insMemberId: String?,
    val latitude: Int?,
    val longitude: Int?,
)