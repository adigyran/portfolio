package com.aya.digital.healthapp.patient.navigation.bottom

import com.aya.digital.core.navigation.bottomnavigation.BottomNavigationMenuProvider
import com.aya.digital.healthapp.patient.R

class BottomNavigationMenuProviderImpl : BottomNavigationMenuProvider {
    override fun getMenu(): Int = R.menu.main_bottom_navigation
    override fun getFakeBadgeId(): Int = R.id.navigation_sppointments
    override fun getProfileId(): Int = R.id.navigation_profile
}