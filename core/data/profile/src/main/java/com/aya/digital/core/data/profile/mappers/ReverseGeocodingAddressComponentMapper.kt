package com.aya.digital.core.data.profile.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.profile.EmergencyContact
import com.aya.digital.core.data.profile.ReverseGeocodingPredictionItem
import com.aya.digital.core.network.model.response.emergencycontact.EmergencyContactResponse
import com.aya.digital.core.network.model.response.geocoding.ReverseGeocodingPredictionItemResponse

abstract class ReverseGeocodingAddressComponentMapper :
    EntityMapper<ReverseGeocodingPredictionItemResponse.AddressComponent, ReverseGeocodingPredictionItem.AddressComponent>