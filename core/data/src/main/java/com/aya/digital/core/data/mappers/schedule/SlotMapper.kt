package com.aya.digital.core.data.mappers.schedule

import com.aya.digital.core.data.model.appointment.AppoinmentSlot
import com.aya.digital.core.network.model.response.SlotResponse
import com.aya.digital.core.util.mappers.base.EntityMapper

abstract class SlotMapper : EntityMapper<SlotResponse, AppoinmentSlot>