package com.aya.digital.core.feature.insurance.list.navigation

import com.aya.digital.core.feature.insurance.list.ui.ProfileInsuranceDoctorView
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen

object ProfileInsuranceDoctorScreen :
    HealthAppFragmentScreen(fragmentCreator = { ProfileInsuranceDoctorView() })
