package com.aya.digital.feature.tabs.appointments.navigation

import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen
import com.aya.digital.core.navigation.screen.HealthAppTabFragmentScreen
import com.aya.digital.feature.tabs.appointments.ui.AppointmentsTabView

object AppointmentsTabScreen : HealthAppTabFragmentScreen(fragmentCreator = { AppointmentsTabView.getNewInstance()})
