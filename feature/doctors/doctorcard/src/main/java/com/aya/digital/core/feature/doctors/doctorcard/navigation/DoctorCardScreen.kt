package com.aya.digital.core.feature.doctors.doctorcard.navigation

import com.aya.digital.core.feature.doctors.doctorcard.ui.DoctorCardView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object DoctorCardScreen :
    HealthAppFragmentScreen(fragmentCreator = { DoctorCardView() })
