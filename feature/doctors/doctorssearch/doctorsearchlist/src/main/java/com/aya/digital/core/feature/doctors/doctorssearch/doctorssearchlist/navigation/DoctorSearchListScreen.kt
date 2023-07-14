package com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.navigation

import com.aya.digital.core.feature.doctors.doctorssearch.doctorssearchlist.ui.DoctorSearchListView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object DoctorSearchListScreen : HealthAppFragmentScreen(fragmentCreator = { DoctorSearchListView() })
