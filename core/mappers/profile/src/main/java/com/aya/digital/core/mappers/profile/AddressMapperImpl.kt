package com.aya.digital.core.mappers.profile

import com.aya.digital.core.data.profile.Address
import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.data.profile.mappers.AddressMapper
import com.aya.digital.core.data.profile.mappers.AvatarMapper
import com.aya.digital.core.data.profile.mappers.RoleMapper
import com.aya.digital.core.network.model.response.profile.AddressResponse
import com.aya.digital.core.network.model.response.profile.AvatarResponse
import com.aya.digital.core.network.model.response.profile.RoleResponse

internal class AddressMapperImpl : AddressMapper() {
    override fun mapFrom(type: AddressResponse): Address =
        Address(
            addressLine = type.addressLine,
            lat = type.lat,
            lon = type.long
        )

}