package com.aya.digital.core.feature.bottomnavhost.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aya.digital.core.feature.bottomnavhost.di.BottomNavHostNavigatorParam
import com.aya.digital.core.feature.bottomnavhost.di.bottomNavHostModule
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostCoordinator
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostNavigator
import com.aya.digital.core.feature.bottomnavhost.viewmodel.BottomNavHostState
import com.aya.digital.core.feature.bottomnavhost.viewmodel.BottomNavHostViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.navigation.coordinator.CoordinatorHolder
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.utils.BackButtonListener
import com.aya.digital.core.navigation.utils.ChildKodeinProvider
import com.aya.digital.core.navigation.utils.ParentRouterProvider
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.feature.bottomnavhost.R
import com.aya.digital.feature.bottomnavhost.databinding.ViewBottomNavHostBinding
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.instance
import org.kodein.di.on

class BottomNavHostView : DiFragment<ViewBottomNavHostBinding, BottomNavHostViewModel, BottomNavHostState, BaseSideEffect>(),
    ParentRouterProvider,
    BackButtonListener,
    ChildKodeinProvider {

    private val coordinatorHolder: CoordinatorHolder by kodein.on(context = this).instance()
    private val coordinator: BottomNavHostCoordinator by kodein.on(context = this).instance()
    private val localCicerone: Cicerone<Router> by kodein.on(context = this).instance()

    private lateinit var navigator: BottomNavHostNavigator
    private val navigatorFactory: ((param: BottomNavHostNavigatorParam) -> BottomNavHostNavigator) by kodein.on(
        context = this
    ).factory()

    private val viewModelFactory: ((Unit) -> BottomNavHostViewModel) by kodein.on(
        context = this
    ).factory()

    override val viewModel: BottomNavHostViewModel = viewModelFactory(Unit)

    private val navListener = NavigationBarView.OnItemSelectedListener { item ->
        false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        navigator =
            navigatorFactory(
                BottomNavHostNavigatorParam(this, binding.navView, navListener) {
                   // tryTyGetParentRouter().sendEvent(Events.Finish)
                }
            )
        return view
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        with(binding.navView)
        {
            setOnItemSelectedListener(navListener)
        }
    }

    override fun onResume() {
        super.onResume()
        localCicerone.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        localCicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        coordinatorHolder.removeCoordinator()
        super.onDestroy()
    }

    override fun getChildKodein(): DI = kodein
    override fun getParentRouter(): CoordinatorRouter = coordinatorHolder

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.mainFragmentContainer)
        return (fragment as? BackButtonListener)?.onBackPressed() == true
    }

    override fun provideDiModule(): DI.Module =  bottomNavHostModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewBottomNavHostBinding = ViewBottomNavHostBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: BottomNavHostState) = Unit
}