package com.aya.digital.core.network.model.request

import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate

@JsonClass(generateAdapter = true)
data class ProfileBody(
    val firstName: String? = null,
    val lastName: String? = null,
    val city: String? = null,
    val state: String? = null,
    val zip: String? = null,
    val birthDate: LocalDate? = null,
    val sex: String? = null,
    val phone: String? = null,
)
