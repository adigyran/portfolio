package com.aya.digital.feature.tabs.doctorsearch.navigation

import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen
import com.aya.digital.core.navigation.screen.HealthAppTabFragmentScreen
import com.aya.digital.feature.tabs.doctorsearch.ui.DoctorSearchTabView

object DoctorSearchTabScreen : HealthAppTabFragmentScreen(fragmentCreator = { DoctorSearchTabView.getNewInstance()})
