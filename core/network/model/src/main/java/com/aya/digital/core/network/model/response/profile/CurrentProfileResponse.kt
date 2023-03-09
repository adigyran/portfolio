package com.aya.digital.core.network.model.response.profile

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate

/*{"id":1082,"email":"testuser1@aya.digital",
"firstName":"David",
"lastName":"Daniels",
"middleName":null,
"jwtUserUuid":"5492cbbf-c15e-4a4e-b88a-b2ab4167a715",
"sex":null,"ssn":null,
"driverLicense":null,"birthDate":null,
"weight":null,"height":null,"tin":null,
"avatar":{"fullUrl":"usr-photo.s3.amazonaws.com/default/default-avatar.jpg","shortUrl":"/default/default-avatar.jpg","contentType":"image/jpeg","originName":"default/default-avatar.jpg"}}*/
@JsonClass(generateAdapter = true)
data class CurrentProfileResponse(
    val id: Int,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val middleName: String?,
    val ssn: String?,
    val sex: String?,
    val tin: String?,
    val jwtUserUuid : String?,
    val weight : String?,
    val height : String?,
    @Json( name = "birthDate") val dateOfBirth: String?,
    val avatar : AvatarResponse?,
    val address : String?
) {

}

@JsonClass(generateAdapter = true)
data class RoleResponse(
    val id: Int,
    val name: String
)


@JsonClass(generateAdapter = true)
data class AvatarResponse(
    val contentType: String?,
    val fullUrl: String?,
    val originName: String?,
    val shortUrl: String?
)