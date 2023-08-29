package com.aya.digital.core.mappers.profile

import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.data.profile.DoctorBio
import com.aya.digital.core.data.profile.mappers.DoctorBioMapper
import com.aya.digital.core.data.profile.mappers.RoleMapper
import com.aya.digital.core.network.model.response.profile.BioResponse
import com.aya.digital.core.network.model.response.profile.RoleResponse

internal class DoctorBioMapperImpl : DoctorBioMapper() {
    override fun mapFrom(type: BioResponse): DoctorBio = DoctorBio(
        aboutInfo = type.aboutText
    )

}