package com.aya.digital.core.uibase.core

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.aya.digital.core.ext.inTransaction
import com.aya.digital.core.navigation.utils.BackButtonListener
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorHolder
import com.aya.digital.core.baseresources.R
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router

abstract class CoreBSContainerFragment<Binding : ViewBinding> :
    CoreBottomSheetDialogFragment<Binding>() {
    //region DI
    //endregion

    protected abstract val localCicerone: Cicerone<Router>

    protected abstract val coordinatorHolder: CoordinatorHolder?
    protected abstract val coordinator: Coordinator?
    protected abstract val navigator: Navigator

    //region Перегруженные методы
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            rootScreen()?.let {
                childFragmentManager.inTransaction {
                    replace(
                        R.id.fragmentContainer,
                        it
                    )
                }
            }
        }
        coordinatorHolder?.setCoordinator(coordinator)
    }

    override fun onResume() {
        super.onResume()
        localCicerone.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        localCicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        coordinatorHolder?.removeCoordinator()
        super.onDestroy()
    }

    override fun onBackPressed(): Boolean {
        if ((childFragmentManager.findFragmentById(
                R.id.fragmentContainer
            ) as? BackButtonListener)?.onBackPressed() != true
        ) {
            localCicerone.router.exit()
        }
        return true
    }

    //endregion

    open fun rootScreen(): Fragment? = null
}