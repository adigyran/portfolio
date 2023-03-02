package com.aya.digital.core.feature.bottomnavhost.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.designsystem.views.navigation.bottom.HealthAppBottomNavigationView
import com.aya.digital.core.ext.closeKeyboard
import com.aya.digital.core.navigation.graph.navigator.BottomNavigationGraph
import com.aya.digital.core.navigation.screen.HealthAppTabFragmentScreen
import com.github.terrakok.cicerone.*
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.navigation.NavigationBarView
import java.lang.ref.WeakReference

class BottomNavHostNavigator(
    private val bottomNavigationGraph: BottomNavigationGraph,
    activity: FragmentActivity,
    fragment: Fragment,
    navView: HealthAppBottomNavigationView,
    containerId: Int,
    private val navListener: NavigationBarView.OnItemSelectedListener,
    private val onExit: () -> Unit,
) : AppNavigator(activity, containerId, fragmentManager = fragment.childFragmentManager) {
    private val fragmentWeak: WeakReference<Fragment>
    private val navViewWeak: WeakReference<HealthAppBottomNavigationView>

    init {
        this.fragmentWeak = WeakReference(fragment)
        this.navViewWeak = WeakReference(navView)
    }

    override fun applyCommands(commands: Array<out Command>) {
        val fragment = fragmentWeak.get()
        if (fragment == null) return
        fragment.requireActivity().runOnUiThread {
            fragment.requireActivity().closeKeyboard()
            super.applyCommands(commands)
        }
    }

    override fun applyCommand(command: Command) {
        (command as? Back)?.let {
            val fragment = fragmentWeak.get()
            if (fragment == null) return

            if (fragment.childFragmentManager.backStackEntryCount > 0) {
                fragment.childFragmentManager.popBackStack()
                return@let
            }
            onExit()
        }

        (command as? Replace)?.let {
            val screen = command.screen

            val navView = navViewWeak.get()
            if (navView == null) return

            openTab(screen)
        }

        (command as? Forward)?.let {
            val fragment = fragmentWeak.get()
            if (fragment == null) return

            val screen = it.screen
            when (screen) {
            }
        }
    }

    private fun openTab(screen: Screen) {
        val fragment = fragmentWeak.get()
        val navView = navViewWeak.get()
        if (screen !is HealthAppTabFragmentScreen || fragment == null || navView == null) return
        navView.setOnItemSelectedListener(null)
        navView.selectedItemId = bottomNavigationGraph.openTab(containerId,fragment, screen,fragmentFactory)
        navView.setOnItemSelectedListener(navListener)

    }
}