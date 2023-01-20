package com.aya.digital.core.data.model.appointment

data class Practitioner(
    val id: Int,
    val firstName: String?,
    val lastName: String?,
    val photo: String?,
    val speciality: String?
)