package com.aya.digital.core.navigation.graph.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.aya.digital.core.navigation.screen.HealthAppTabFragmentScreen

interface BottomNavigationGraph {
    fun openTab(
        containerId: Int,
        fragment: Fragment,
        screen: HealthAppTabFragmentScreen,
        fragmentFactory: FragmentFactory
    ):Int
}