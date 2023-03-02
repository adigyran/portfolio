package com.aya.digital.core.navigation.bottomnavigation

import com.aya.digital.core.navigation.coordinator.CoordinatorEvent

interface BottomNavigationItemListener {
    fun onItemSelected(itemId:Int) : CoordinatorEvent
}