package com.aya.digital.feature.rootcontainer.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.baseresources.databinding.ViewFragmentContainerBinding
import com.aya.digital.feature.rootcontainer.di.RootNavigatorParam
import com.aya.digital.feature.rootcontainer.di.rootContainerDiModule
import com.aya.digital.feature.rootcontainer.navigation.RootNavigator
import com.aya.digital.feature.rootcontainer.viewmodel.RootContainerState
import com.aya.digital.feature.rootcontainer.viewmodel.RootContainerViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.navigation.AppFlavour
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.graph.DefaultRootScreenManager
import com.aya.digital.core.navigation.utils.BackButtonListener
import com.aya.digital.core.ui.base.screens.DiActivity
import com.aya.digital.core.util.requestcodes.RequestCodes
import com.github.terrakok.cicerone.Navigator
import org.kodein.di.factory
import org.kodein.di.instance
import org.kodein.di.on

class RootView :
    DiActivity<ViewFragmentContainerBinding, RootContainerViewModel, RootContainerState, BaseSideEffect>() {

    private val appFlavour: AppFlavour by kodein.on(context = this).instance()
    private val defaultRootScreenManager: DefaultRootScreenManager by kodein.on(context = this).instance("root_navigation")


    private val viewModelFactory: ((Unit) -> RootContainerViewModel) by kodein.on(
        context = this
    ).factory()

    private val coordinatorFactory: ((fragmentManager: FragmentManager) -> Coordinator) by kodein.on(
        context = this
    ).factory()

    private val navigatorFactory: ((param: RootNavigatorParam) -> RootNavigator) by kodein.on(
        context = this
    ).factory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null)
        {
            openDefaultScreen()
            viewModel.listenForToken()
        }
    }

    private fun openDefaultScreen() {
        viewModel.openDefaultScreen()
    }


    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: RootContainerState) = Unit

    override fun provideDiModule() = rootContainerDiModule()

    override fun provideViewBinding(inflater: LayoutInflater): ViewFragmentContainerBinding =
        ViewFragmentContainerBinding.inflate(inflater)

    override fun provideViewModel(): RootContainerViewModel = viewModelFactory(Unit)

    override fun provideNavigator(): Navigator = navigatorFactory(
        RootNavigatorParam(
            this,
            binding.fragmentContainer.id
        )
    )

    /*onBackPressedDispatcher.addCallback(this *//* lifecycle owner *//*) {
        // Back is pressed... Finishing the activity
        finish()
    }
    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(com.aya.digital.core.baseresources.R.id.fragmentContainer)
        if ((fragment as? BackButtonListener)?.onBackPressed() == true) {
            return
        }
        localCicerone.router.exit()
    }*/
    override fun provideCoordinator(): Coordinator = coordinatorFactory(supportFragmentManager)

}
