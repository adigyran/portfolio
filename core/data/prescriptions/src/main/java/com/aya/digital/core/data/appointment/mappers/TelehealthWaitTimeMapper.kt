package com.aya.digital.core.data.appointment.mappers

import com.aya.digital.core.data.appointment.AppoinmentSlot
import com.aya.digital.core.data.appointment.TelehealthWaitTimeModel
import com.aya.digital.core.data.base.mappers.EntityMapper
import com.aya.digital.core.network.model.response.schedule.SlotResponse
import com.aya.digital.core.network.model.response.telehealth.TelehealthWaitTimeResponse

abstract class TelehealthWaitTimeMapper : EntityMapper<TelehealthWaitTimeResponse, TelehealthWaitTimeModel>