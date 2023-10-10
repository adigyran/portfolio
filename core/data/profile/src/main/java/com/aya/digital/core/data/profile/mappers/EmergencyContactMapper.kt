package com.aya.digital.core.data.profile.mappers

import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.data.profile.EmergencyContact
import com.aya.digital.core.network.model.response.emergencycontact.EmergencyContactResponse

abstract class EmergencyContactMapper :
    EntityMapper<EmergencyContactResponse, EmergencyContact>