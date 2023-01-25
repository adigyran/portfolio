package com.aya.digital.core.feature.container.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.ext.closeKeyboard
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator

class RootNavigator(
    activity: FragmentActivity,
    containerId: Int,
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
}
