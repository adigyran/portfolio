package com.aya.digital.core.feature.tabviews.doctorsearchcontainer.navigation

import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.ui.DoctorSearchContainerView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object DoctorSearchContainerScreen : HealthAppFragmentScreen(fragmentCreator = { DoctorSearchContainerView.getNewInstance() })
