package com.aya.digital.patientapp.app.root

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Forward
import com.github.terrakok.cicerone.androidx.AppNavigator

class RootNavigator(activity: FragmentActivity, @IdRes containerId: Int) :
    AppNavigator(activity, containerId) {

    override fun applyCommand(command: Command) {
        when (command) {
            is Forward -> {
                val screen = command.screen
            }
        }
    }
}