package com.aya.digital.core.network.model.response.doctors

import com.aya.digital.core.network.model.response.base.Pageable
import com.aya.digital.core.network.model.response.base.SortX
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate
import retrofit2.http.Field

@JsonClass(generateAdapter = true)
data class PractitionersResponse(
    @Json(name = "content")
    val content: List<DoctorDataResponse>?,
    val empty: Boolean?,
    val first: Boolean?,
    val last: Boolean?,
    val number: Int?,
    val numberOfElements: Int?,
    val pageable: Pageable?,
    val size: Int?,
    val sort: SortX?,
    val totalElements: Int?,
    val totalPages: Int?
)

data class DoctorDataResponse(
    val addressLine1: String?,
    val addressLine2: String?,
    val city: String?,
    val dateOfBirth: LocalDate?,
    val emailAddress: String?,
    val firstName: String?,
    val id: Int?,
    val lastName: String?,
    val middleName: String?,
    val mobilePhoneNumber: String?,
    val phoneNumber: String?,
    val photo: String?,
    val primaryFacilityName: Any?,
    val about: String?,
    val education: String?,
    val sex: String?,
    @Field("speciality") val speciality: DoctorSpeciality?,
    val state: String?,
    val zip: String?,
    val location: LocationResponse?,
)

data class DoctorSpeciality(
    val id: Int,
    val code: String,
    val name: String,
)