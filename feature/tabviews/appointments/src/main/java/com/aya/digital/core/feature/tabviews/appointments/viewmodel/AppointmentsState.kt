package com.aya.digital.core.feature.tabviews.appointments.viewmodel

import com.aya.digital.core.domain.appointment.base.model.AppointmentModel
import com.aya.digital.core.mvi.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class AppointmentsState(val appointments:List<AppointmentModel>?=null) : BaseState
