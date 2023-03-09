package com.aya.digital.core.network.model.request

import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate

@JsonClass(generateAdapter = true)
data class ProfileBody(
    val firstName: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
    val dateOfBirth: String? = null,
    val sex: String? = null,
    val height: String? = null,
    val weight: String? = null,
    val shortAddress: String? = null,
    val ssn:String? = null,
    val tin:String? = null,
)
