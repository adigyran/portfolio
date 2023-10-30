package com.aya.digital.core.mappers.profile

import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.data.profile.ReverseGeocodingPredictionItem
import com.aya.digital.core.data.profile.mappers.AvatarMapper
import com.aya.digital.core.data.profile.mappers.CurrentProfileMapper
import com.aya.digital.core.data.profile.mappers.ReverseGeocodingAddressComponentMapper
import com.aya.digital.core.data.profile.mappers.RoleMapper
import com.aya.digital.core.network.model.response.geocoding.ReverseGeocodingPredictionItemResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.util.datetime.DateTimeUtils
import java.time.LocalDate


internal class ReverseGeocodingAddressComponentMapperImpl() :
    ReverseGeocodingAddressComponentMapper() {
    override fun mapFrom(type: ReverseGeocodingPredictionItemResponse.AddressComponent): ReverseGeocodingPredictionItem.AddressComponent =
        ReverseGeocodingPredictionItem.AddressComponent(
            longName = type.longName,
            shortName = type.shortName,
            types = type.types
        )
}