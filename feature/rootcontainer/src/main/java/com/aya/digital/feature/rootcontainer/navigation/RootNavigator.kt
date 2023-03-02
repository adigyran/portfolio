package com.aya.digital.feature.rootcontainer.navigation

import android.content.ActivityNotFoundException
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.ext.closeKeyboard
import com.aya.digital.core.navigation.graph.navigator.RootNavigationGraph
import com.aya.digital.core.navigation.screen.*
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Forward
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import timber.log.Timber

class RootNavigator(
    activity: FragmentActivity,
    containerId: Int,
    private val rootNavigationGraph: RootNavigationGraph
) : AppNavigator(activity, containerId) {

    override fun applyCommands(commands: Array<out Command>) {
        activity.runOnUiThread {
            activity.closeKeyboard()
            super.applyCommands(commands)
        }
    }

    override fun activityBack() {
        activity.moveTaskToBack(true)
    }


    override fun forward(command: Forward) {
        if (command.screen is HealthAppBottomNavHostFragmentScreen) {
            val screen = command.screen as HealthAppBottomNavHostFragmentScreen
            val fragment = screen.createFragment(fragmentFactory)
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction
                .replace(containerId, fragment, "BottomNavHost")
                .addToBackStack(screen.screenKey)
                .commit()
            return
        }
        super.forward(command)
    }


    override fun replace(command: Replace) {
        if (command.screen is HealthAppContainerFragmentScreen) {
            val screen = command.screen as HealthAppContainerFragmentScreen
            when (screen) {
                is HealthAppBottomNavHostFragmentScreen -> {
                    val fragment = screen.createFragment(fragmentFactory)
                    if(fragmentManager.backStackEntryCount > 0)
                    {
                        fragmentManager.popBackStack()

                        fragmentManager.beginTransaction()
                            .replace(containerId, fragment, "BottomNavHost")
                            .addToBackStack(screen.screenKey)
                            .commit()
                    }
                    else {
                        fragmentManager.beginTransaction()
                            .replace(containerId, fragment, "BottomNavHost")
                            .commit()
                    }
                    return
                }
                is HealthAppAuthContainerFragmentScreen -> {
                    val fragment = screen.createFragment(fragmentFactory)
                    fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    fragmentManager.beginTransaction()
                        .replace(containerId, fragment, "LoginFlow")
                        .commit()
                    return
                }
            }
        }
        super.replace(command)
    }

    override fun applyCommand(command: Command) {
        if (command is Forward) {
            when (command.screen) {
                is HealthAppActionScreen -> {
                    val screen = command.screen as HealthAppActionScreen
                    val intent = rootNavigationGraph.openAction(screen)
                    try {
                        activity.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        Timber.e(e)
                    }
                    return
                }
                is HealthAppDialogFragmentScreen -> {
                    val screen = command.screen as HealthAppDialogFragmentScreen
                    val fragment = screen.createFragment(fragmentFactory) as BottomSheetDialogFragment
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(screen.screenKey)
                    fragment.show(fragmentTransaction,screen.tag)
                    return
                }
            }
        }
        super.applyCommand(command)
    }


}
