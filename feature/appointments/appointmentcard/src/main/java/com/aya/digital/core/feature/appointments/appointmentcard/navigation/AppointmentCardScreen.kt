package com.aya.digital.core.feature.appointments.appointmentcard.navigation

import com.aya.digital.core.feature.appointments.appointmentcard.ui.AppointmentCardView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

data class AppointmentCardScreen(val appointmentId: Int) :
    HealthAppFragmentScreen(fragmentCreator = { AppointmentCardView.getNewInstance(appointmentId = appointmentId) })
