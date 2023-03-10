package com.aya.digital.core.feature.bottomnavhost.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.aya.digital.core.ext.argument
import com.aya.digital.core.ext.createFragment
import com.aya.digital.core.feature.bottomnavhost.di.BottomNavHostNavigatorParam
import com.aya.digital.core.feature.bottomnavhost.di.bottomNavHostModule
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostCoordinator
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostNavigationEvents
import com.aya.digital.core.feature.bottomnavhost.navigation.BottomNavHostNavigator
import com.aya.digital.core.feature.bottomnavhost.ui.model.BottomNavHostStateTransformer
import com.aya.digital.core.feature.bottomnavhost.viewmodel.BottomNavHostState
import com.aya.digital.core.feature.bottomnavhost.viewmodel.BottomNavHostViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.mvi.StubUiModel
import com.aya.digital.core.navigation.bottomnavigation.BottomNavigationItemListener
import com.aya.digital.core.navigation.bottomnavigation.BottomNavigationMenuProvider
import com.aya.digital.core.navigation.bottomnavigation.StartScreen
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.navigation.coordinator.CoordinatorHolder
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.navigation.graph.DefaultBottomNavScreenManager
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
import kotlinx.parcelize.Parcelize
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.instance
import org.kodein.di.on

class BottomNavHostView : DiFragment<ViewBottomNavHostBinding, BottomNavHostViewModel, BottomNavHostState, BaseSideEffect, StubUiModel, BottomNavHostStateTransformer>(),
    ParentRouterProvider,
    BackButtonListener,
    ChildKodeinProvider {

    private var param: Param by argument("param")


    private val coordinatorFactory: ((fragmentManager: FragmentManager) -> Coordinator) by kodein.on(
        context = this
    ).factory()

    private val coordinatorHolder: CoordinatorHolder by kodein.on(context = this as Fragment).instance()
    private val coordinator: Coordinator by kodein.on(context = this as Fragment).instance()
    private val localCicerone: Cicerone<Router> by kodein.on(context = this as Fragment).instance()

    private val itemListener: BottomNavigationItemListener by kodein.on(context = this).instance()
    private val menuProvider: BottomNavigationMenuProvider by kodein.on(context = this).instance()
    private val defaultBottomNavScreenManager: DefaultBottomNavScreenManager by kodein.on(context = this).instance()


    private lateinit var navigator: BottomNavHostNavigator
    private val navigatorFactory: ((param: BottomNavHostNavigatorParam) -> BottomNavHostNavigator) by kodein.on(
        context = this
    ).factory()

    private val viewModelFactory: ((Unit) -> BottomNavHostViewModel) by kodein.on(
        context = this
    ).factory()

    private val navListener = NavigationBarView.OnItemSelectedListener { item ->
        coordinatorHolder.sendEvent(itemListener.onItemSelected(item.itemId))
        false
    }

    private val stateTransformerFactory: ((Unit) -> BottomNavHostStateTransformer) by kodein.on(
        context = this
    ).factory()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            localCicerone.router.replaceScreen(defaultBottomNavScreenManager.processDefaultBottomNavScreen(param.startScreen))
        }
        coordinatorHolder.setCoordinator(coordinator)
        coordinatorHolder.setRouter(localCicerone.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        navigator =
            navigatorFactory(
                BottomNavHostNavigatorParam(requireActivity(),this, binding.navView,binding.mainFragmentContainer.id,navListener) {
                   tryTyGetParentRouter().sendEvent(BottomNavHostNavigationEvents.Finish)
                }
            )
        return view
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        prepareBottomBar()
    }

    private fun prepareBottomBar() {
        binding.navView.menu.clear()
        binding.navView.inflateMenu(menuProvider.getMenu())
        with(binding.navView)
        {
            setOnItemSelectedListener(navListener)
        }
    }

    override fun provideViewModel(): BottomNavHostViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): BottomNavHostStateTransformer = stateTransformerFactory(Unit)

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
        coordinatorHolder.removeRouter()
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

    @Parcelize
    class Param(val startScreen: StartScreen) : Parcelable

    companion object {
        fun getNewInstance(startScreen: StartScreen): BottomNavHostView  = createFragment(Param(startScreen))
    }
}