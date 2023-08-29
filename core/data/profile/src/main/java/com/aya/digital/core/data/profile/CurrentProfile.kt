package com.aya.digital.core.data.profile

import com.aya.digital.core.network.model.response.profile.AvatarResponse
import java.time.LocalDate

data class CurrentProfile(
    val id: Int,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val middleName: String?,
    val ssn: String?,
    val sex: String?,
    val tin: String?,
    val jwtUserUuid: String?,
    val weight: String?,
    val height: String?,
    val dateOfBirth: LocalDate?,
    val avatar: Avatar?,
    val shortAddress : String?
) {
    data class Role(
        val id: Int,
        val name: String
    )

    data class Avatar(
        val contentType: String?,
        val fullUrl: String?,
        val originName: String?,
        val shortUrl: String?
    )
}
