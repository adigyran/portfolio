package com.aya.digital.core.feature.tabviews.doctorsearch.navigation

import com.aya.digital.core.feature.tabviews.doctorsearch.ui.DoctorSearchView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object DoctorSearchScreen : HealthAppFragmentScreen(fragmentCreator = { DoctorSearchView() })
