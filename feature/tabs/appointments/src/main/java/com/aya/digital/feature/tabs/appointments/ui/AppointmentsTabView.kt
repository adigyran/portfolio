package com.aya.digital.feature.tabs.appointments.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.baseresources.databinding.ViewFragmentContainerBinding
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.navigation.CustomNavigator
import com.aya.digital.core.navigation.di.CustomNavigatorParam
import com.aya.digital.core.ui.base.screens.DiContainerFragment
import com.aya.digital.core.ui.base.screens.DiTabContainerFragment
import com.aya.digital.feature.tabs.appointments.di.appointmentsTabDiModule
import com.aya.digital.feature.tabs.appointments.navigation.AppointmentsTabNavigationEvents
import com.aya.digital.feature.tabs.appointments.viewmodel.AppointmentsTabState
import com.aya.digital.feature.tabs.appointments.viewmodel.AppointmentsTabViewModel
import com.github.terrakok.cicerone.Navigator
import org.kodein.di.factory
import org.kodein.di.on
import com.aya.digital.core.baseresources.R as BaseresourcesR

class AppointmentsTabView :
    DiTabContainerFragment<ViewFragmentContainerBinding, AppointmentsTabViewModel, AppointmentsTabState, BaseSideEffect>() {


    private val navigatorFactory: ((param: CustomNavigatorParam) -> CustomNavigator) by kodein.on(
        context = this
    ).factory()
    private val viewModelFactory: ((Unit) -> AppointmentsTabViewModel) by kodein.on(
        context = this
    ).factory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()

    }

    private fun openDefaultScreen() {
        coordinatorHolder.sendEvent(AppointmentsTabNavigationEvents.OpenDefaultScreen)
    }


    override fun provideNavigator(): Navigator =
        navigatorFactory(
            CustomNavigatorParam(
                requireActivity(),
                childFragmentManager,
                BaseresourcesR.id.fragmentContainer
            ) {
                tryTyGetParentRouter().sendEvent(AppointmentsTabNavigationEvents.Finish)
            })

    override fun provideDiModule() = appointmentsTabDiModule(tryTyGetParentRouter())

    override fun resetState() = coordinatorHolder.sendEvent(AppointmentsTabNavigationEvents.ResetState)

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewFragmentContainerBinding =
        ViewFragmentContainerBinding.inflate(inflater, container, false)

    override fun provideViewModel() = viewModelFactory(Unit)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: AppointmentsTabState) = Unit

    companion object {

        fun getNewInstance(): AppointmentsTabView {
            return AppointmentsTabView().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }
}

