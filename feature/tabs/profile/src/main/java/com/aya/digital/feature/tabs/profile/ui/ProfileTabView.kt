package com.aya.digital.feature.tabs.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aya.digital.core.baseresources.R as BaseResourcesR
import com.aya.digital.core.baseresources.databinding.ViewFragmentContainerBinding
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.navigation.CustomNavigator
import com.aya.digital.core.navigation.di.CustomNavigatorParam
import com.aya.digital.core.navigation.graph.DefaultRootScreenManager
import com.aya.digital.core.ui.base.screens.DiContainerFragment
import com.aya.digital.core.ui.base.screens.DiTabContainerFragment
import com.aya.digital.feature.tabs.profile.di.profileTabDiModule
import com.aya.digital.feature.tabs.profile.navigation.ProfileTabNavigationEvents
import com.aya.digital.feature.tabs.profile.viewmodel.ProfileTabState
import com.aya.digital.feature.tabs.profile.viewmodel.ProfileTabViewModel
import com.github.terrakok.cicerone.Navigator
import org.kodein.di.factory
import org.kodein.di.instance
import org.kodein.di.on
import timber.log.Timber

class ProfileTabView :
    DiTabContainerFragment<ViewFragmentContainerBinding, ProfileTabViewModel, ProfileTabState, BaseSideEffect>() {


    private val navigatorFactory: ((param: CustomNavigatorParam) -> CustomNavigator) by kodein.on(
        context = this
    ).factory()
    private val viewModelFactory: ((Unit) -> ProfileTabViewModel) by kodein.on(
        context = this
    ).factory()

    private val defaultRootScreenManager: DefaultRootScreenManager by kodein.on(context = this).instance("profile_tab_navigation")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()

    }


    private fun openDefaultScreen() {
        Timber.d("openDefaultScreen")
        coordinatorHolder.sendEvent(ProfileTabNavigationEvents.OpenDefaultScreen)
    }


    override fun provideNavigator(): Navigator =
        navigatorFactory(
            CustomNavigatorParam(
                requireActivity(),
                childFragmentManager,
                BaseResourcesR.id.fragmentContainer
            ) {
                tryTyGetParentRouter().sendEvent(ProfileTabNavigationEvents.Finish)
            })

    override fun rootScreen(): Fragment = defaultRootScreenManager.processDefaultRootScreen().createFragment(childFragmentManager.fragmentFactory)

    override fun provideDiModule() = profileTabDiModule(tryTyGetParentRouter())

    override fun resetState() = coordinatorHolder.sendEvent(ProfileTabNavigationEvents.ResetState)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewFragmentContainerBinding =
        ViewFragmentContainerBinding.inflate(inflater, container, false)

    override fun provideViewModel() = viewModelFactory(Unit)

    override fun sideEffect(sideEffect: BaseSideEffect)=Unit

    override fun render(state: ProfileTabState) = Unit

    companion object {

        fun getNewInstance(): ProfileTabView {
            return ProfileTabView().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }
}

