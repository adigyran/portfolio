package com.aya.digital.core.data.appointment

data class Practitioner(
    val id: Int,
    val firstName: String?,
    val lastName: String?,
    val photo: String?,
    val speciality: String?
)