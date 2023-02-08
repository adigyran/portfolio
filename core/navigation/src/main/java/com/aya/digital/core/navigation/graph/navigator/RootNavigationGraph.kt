package com.aya.digital.core.navigation.graph.navigator

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.navigation.screen.HealthAppActionScreen
import com.aya.digital.core.navigation.screen.HealthAppContainerFragmentScreen
import com.aya.digital.core.navigation.screen.HealthAppFragmentScreen
import com.github.terrakok.cicerone.Screen

interface RootNavigationGraph {
    fun openAction(screen: HealthAppActionScreen) : Intent

}