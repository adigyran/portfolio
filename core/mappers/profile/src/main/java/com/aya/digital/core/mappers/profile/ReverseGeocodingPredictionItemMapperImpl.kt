package com.aya.digital.core.mappers.profile

import com.aya.digital.core.data.profile.CurrentProfile
import com.aya.digital.core.data.profile.ReverseGeocodingPredictionItem
import com.aya.digital.core.data.profile.mappers.AvatarMapper
import com.aya.digital.core.data.profile.mappers.CurrentProfileMapper
import com.aya.digital.core.data.profile.mappers.ReverseGeocodingAddressComponentMapper
import com.aya.digital.core.data.profile.mappers.ReverseGeocodingPredictionItemMapper
import com.aya.digital.core.data.profile.mappers.RoleMapper
import com.aya.digital.core.network.model.response.geocoding.ReverseGeocodingPredictionItemResponse
import com.aya.digital.core.network.model.response.profile.CurrentProfileResponse
import com.aya.digital.core.util.datetime.DateTimeUtils
import java.time.LocalDate


internal class ReverseGeocodingPredictionItemMapperImpl(private val addressComponentMapper: ReverseGeocodingAddressComponentMapper) :
    ReverseGeocodingPredictionItemMapper() {
    override fun mapFrom(type: ReverseGeocodingPredictionItemResponse): ReverseGeocodingPredictionItem =
        ReverseGeocodingPredictionItem(
            addressComponents = type.addressComponents?.let { addressComponentMapper.mapFromList(it) },
            formattedAddress = type.formattedAddress ?: "",
            types = type.types,
            partialMatch = type.partialMatch,
            placeId = type.placeId?:""
        )
}