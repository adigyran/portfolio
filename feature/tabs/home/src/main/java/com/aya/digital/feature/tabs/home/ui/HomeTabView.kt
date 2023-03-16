package com.aya.digital.feature.tabs.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aya.digital.core.baseresources.databinding.ViewFragmentContainerBinding
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.navigation.CustomNavigator
import com.aya.digital.core.navigation.di.CustomNavigatorParam
import com.aya.digital.core.navigation.graph.DefaultRootScreenManager
import com.aya.digital.core.ui.base.screens.DiContainerFragment
import com.aya.digital.core.ui.base.screens.DiTabContainerFragment
import com.aya.digital.feature.tabs.home.di.homeTabDiModule
import com.aya.digital.feature.tabs.home.navigation.HomeTabNavigationEvents
import com.aya.digital.feature.tabs.home.viewmodel.HomeTabState
import com.aya.digital.feature.tabs.home.viewmodel.HomeTabViewModel
import com.github.terrakok.cicerone.Navigator
import org.kodein.di.factory
import org.kodein.di.instance
import org.kodein.di.on
import com.aya.digital.core.baseresources.R as BaseresourcesR

class HomeTabView :
    DiTabContainerFragment<ViewFragmentContainerBinding, HomeTabViewModel, HomeTabState, BaseSideEffect>() {


    private val navigatorFactory: ((param: CustomNavigatorParam) -> CustomNavigator) by kodein.on(
        context = this
    ).factory()
    private val viewModelFactory: ((Unit) -> HomeTabViewModel) by kodein.on(
        context = this
    ).factory()

    private val defaultRootScreenManager: DefaultRootScreenManager by kodein.on(context = this).instance("home_tab_navigation")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
    }

    private fun openDefaultScreen() {
        coordinatorHolder.sendEvent(HomeTabNavigationEvents.OpenDefaultScreen)
    }


    override fun rootScreen(): Fragment = defaultRootScreenManager.processDefaultRootScreen().createFragment(childFragmentManager.fragmentFactory)


    override fun provideNavigator(): Navigator =
        navigatorFactory(
            CustomNavigatorParam(
                requireActivity(),
                childFragmentManager,
                BaseresourcesR.id.fragmentContainer
            ) {
                tryTyGetParentRouter().sendEvent(HomeTabNavigationEvents.Finish)
            })

    override fun provideDiModule() = homeTabDiModule(tryTyGetParentRouter())
    override fun resetState() = coordinatorHolder.sendEvent(HomeTabNavigationEvents.ResetState)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewFragmentContainerBinding =
        ViewFragmentContainerBinding.inflate(inflater, container, false)

    override fun provideViewModel() = viewModelFactory(Unit)

    override fun sideEffect(sideEffect: BaseSideEffect)=Unit

    override fun render(state: HomeTabState) = Unit

    companion object {

        fun getNewInstance(): HomeTabView {
            return HomeTabView().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }
}

