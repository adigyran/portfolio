package com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.navigation

import com.aya.digital.core.feature.doctors.doctorssearch.doctorsearchmap.ui.DoctorSearchMapView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object DoctorSearchMapScreen : HealthAppFragmentScreen(fragmentCreator = { DoctorSearchMapView() })
