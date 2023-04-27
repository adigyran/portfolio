package com.aya.digital.healthapp.doctor.navigation.bottom

import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostNavigationEvents
import com.aya.digital.core.navigation.bottomnavigation.BottomNavigationItemListener
import com.aya.digital.core.navigation.coordinator.CoordinatorEvent
import com.aya.digital.healthapp.doctor.R

class BottomNavigationItemListenerImpl : BottomNavigationItemListener {
    override fun onItemSelected(itemId: Int): CoordinatorEvent =
        when(itemId)
        {
            R.id.navigation_sppointments -> BottomNavHostNavigationEvents.OpenAppointments
            R.id.navigation_profile -> BottomNavHostNavigationEvents.OpenProfile
            else -> BottomNavHostNavigationEvents.OpenAppointments
        }
}