package com.aya.digital.core.feature.doctors.doctorcard.navigation

import com.aya.digital.core.feature.doctors.doctorcard.ui.DoctorCardView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

data class DoctorCardScreen(val doctorId: Int) :
    HealthAppFragmentScreen(fragmentCreator = { DoctorCardView.getNewInstance(doctorId = doctorId) })
