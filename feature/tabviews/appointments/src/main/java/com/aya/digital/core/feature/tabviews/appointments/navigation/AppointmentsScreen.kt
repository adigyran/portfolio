package com.aya.digital.core.feature.tabviews.appointments.navigation

import com.aya.digital.core.feature.tabviews.appointments.ui.AppointmentsView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object AppointmentsScreen : HealthAppFragmentScreen(fragmentCreator = { AppointmentsView() })
