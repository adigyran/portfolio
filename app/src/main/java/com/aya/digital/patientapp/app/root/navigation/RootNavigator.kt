package com.aya.digital.patientapp.app.root.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import com.aya.digital.core.navigation.screen.PatientAppDialogScreen
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Forward
import com.github.terrakok.cicerone.androidx.AppNavigator

class RootNavigator(activity: FragmentActivity, @IdRes containerId: Int) :
    AppNavigator(activity, containerId) {

    override fun applyCommand(command: Command) {
        when (command) {
            is Forward -> {
                return
                //TODO navigation
               /* val screen = command.screen
                if (screen is PatientAppDialogScreen) {
                    val transaction =
                        fragmentManager.beginTransaction().addToBackStack(null)
                    screen.appScreen.
                    return
                }*/
            }
        }
        super.applyCommand(command)
    }
}