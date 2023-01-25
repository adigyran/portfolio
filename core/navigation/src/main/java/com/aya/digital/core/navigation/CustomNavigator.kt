package com.aya.digital.core.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.ext.closeKeyboard
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.kodein.di.DIAware
import java.lang.ref.WeakReference

/**
 * TODO
 *
 * @property onExit
 * @constructor
 * TODO
 *
 * @param activity
 * @param containerId
 * @param fragmentManager
 */
open class CustomNavigator(
    activity: FragmentActivity,
    containerId: Int,
    fragmentManager: FragmentManager,
    val onExit: (() -> Unit)?
) : AppNavigator(activity, containerId, fragmentManager) {


    private val activityWeak: WeakReference<FragmentActivity>

    init {
        this.activityWeak = WeakReference(activity)
    }

    override fun applyCommands(commands: Array<out Command>) {
        val activity = activityWeak.get()
        if (activity == null) return
        activity.runOnUiThread {
            activity.closeKeyboard()
            super.applyCommands(commands)
        }
    }

    override fun activityBack()  = onExit?.invoke() ?: super.activityBack()
}