package com.aya.digital.core.feature.tabviews.appointmentsscheduler.navigation

import com.aya.digital.core.feature.tabviews.appointmentsscheduler.ui.AppointmentsSchedulerView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object AppointmentsSchedulerScreen : HealthAppFragmentScreen(fragmentCreator = { AppointmentsSchedulerView() })
