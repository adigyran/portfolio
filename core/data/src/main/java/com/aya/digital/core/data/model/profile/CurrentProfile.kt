package com.aya.digital.core.data.model.profile

import kotlinx.datetime.LocalDate

data class CurrentProfile(val id: Int,
                          val email: String?,
                          val firstName: String?,
                          val lastName: String?,
                          val middleName: String?,
                          val ssn: String?,
                          val sex: String?,
                          val sexAtBirth: String?,
                          val dateOfBirth: LocalDate?,
                          val driverLicense: String?,
                          val phoneNumber: String?,
                          val avatar: String?,
                          val emergencyContactEnabled: Boolean,
                          val emergencyContactName: String?,
                          val emergencyContactPhone: String?,
                          val roles: List<Role>)
{
    val patientId: Int? get() = roles.firstOrNull { it.name == "Patient" }?.id
    val doctorId: Int? get() = roles.firstOrNull { it.name == "Practitioner" }?.id
    data class Role(
        val id: Int,
        val name: String
    )
}
