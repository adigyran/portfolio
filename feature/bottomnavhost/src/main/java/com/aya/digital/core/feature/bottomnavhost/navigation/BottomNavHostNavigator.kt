package com.aya.digital.core.feature.bottomnavhost.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.designsystem.views.navigation.bottom.HealthAppBottomNavigationView
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import java.lang.ref.WeakReference

class BottomNavHostNavigator(
    fragment: Fragment,
    navView: HealthAppBottomNavigationView,
    private val navListener: NavigationBarView.OnItemSelectedListener,
    private val onExit: () -> Unit
) : Navigator {
    private val fragmentWeak: WeakReference<Fragment>
    private val navViewWeak: WeakReference<HealthAppBottomNavigationView>

    init {
        this.fragmentWeak = WeakReference(fragment)
        this.navViewWeak = WeakReference(navView)
    }

    override fun applyCommands(commands: Array<out Command>) {
        TODO("Not yet implemented")
    }
}