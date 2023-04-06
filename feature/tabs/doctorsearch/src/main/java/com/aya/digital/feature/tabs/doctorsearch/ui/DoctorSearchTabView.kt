package com.aya.digital.feature.tabs.doctorsearch.ui

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
import com.aya.digital.feature.tabs.doctorsearch.di.doctorSearchTabDiModule
import com.aya.digital.feature.tabs.doctorsearch.navigation.DoctorSearchTabNavigationEvents
import com.aya.digital.feature.tabs.doctorsearch.viewmodel.DoctorSearchTabState
import com.aya.digital.feature.tabs.doctorsearch.viewmodel.DoctorSearchTabViewModel
import com.github.terrakok.cicerone.Navigator
import org.kodein.di.factory
import org.kodein.di.instance
import org.kodein.di.on
import com.aya.digital.core.baseresources.R as BaseresourcesR

class DoctorSearchTabView :
    DiTabContainerFragment<ViewFragmentContainerBinding, DoctorSearchTabViewModel, DoctorSearchTabState, BaseSideEffect>() {


    private val navigatorFactory: ((param: CustomNavigatorParam) -> CustomNavigator) by kodein.on(
        context = this
    ).factory()
    private val viewModelFactory: ((Unit) -> DoctorSearchTabViewModel) by kodein.on(
        context = this
    ).factory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()

    }

    private val defaultRootScreenManager: DefaultRootScreenManager by kodein.on(context = this).instance("doctors_search_tab_navigation")


    private fun openDefaultScreen() {
        coordinatorHolder.sendEvent(DoctorSearchTabNavigationEvents.OpenDefaultScreen)
    }

    override fun rootScreen(): Fragment = defaultRootScreenManager.processDefaultRootScreen().createFragment(childFragmentManager.fragmentFactory)


    override fun provideNavigator(): Navigator =
        navigatorFactory(
            CustomNavigatorParam(
                requireActivity(),
                childFragmentManager,
                BaseresourcesR.id.fragmentContainer
            ) {
                tryTyGetParentRouter().sendEvent(DoctorSearchTabNavigationEvents.Finish)
            })

    override fun provideDiModule() = doctorSearchTabDiModule(tryTyGetParentRouter())

    override fun resetState() = coordinatorHolder.sendEvent(DoctorSearchTabNavigationEvents.ResetState)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewFragmentContainerBinding =
        ViewFragmentContainerBinding.inflate(inflater, container, false)

    override fun provideViewModel() = viewModelFactory(Unit)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: DoctorSearchTabState) = Unit

    companion object {

        fun getNewInstance(): DoctorSearchTabView {
            return DoctorSearchTabView().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }
}

