package com.aya.digital.core.feature.tabviews.doctorsearchcontainer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.baseresources.R
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.databinding.ViewDoctorsearchContainerBinding
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.di.doctorSearchContainerDiModule
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.navigation.DoctorSearchContainerNavigationEvents
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.viewmodel.DoctorSearchContainerState
import com.aya.digital.core.feature.tabviews.doctorsearchcontainer.viewmodel.DoctorSearchContainerViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.navigation.CustomNavigator
import com.aya.digital.core.navigation.di.CustomNavigatorParam
import com.aya.digital.core.ui.base.screens.DiContainerFragment
import com.github.terrakok.cicerone.Navigator
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on


class DoctorSearchContainerView :
    DiContainerFragment<ViewDoctorsearchContainerBinding, DoctorSearchContainerViewModel, DoctorSearchContainerState, BaseSideEffect>() {


    private val navigatorFactory: ((param: CustomNavigatorParam) -> CustomNavigator) by kodein.on(
        context = this
    ).factory()
    private val viewModelFactory: ((Unit) -> DoctorSearchContainerViewModel) by kodein.on(
        context = this
    ).factory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
        openDefaultScreen()

    }

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    private fun openDefaultScreen() {
        coordinatorHolder.sendEvent(DoctorSearchContainerNavigationEvents.OpenDefaultScreen)
    }


    override fun provideNavigator(): Navigator =
        navigatorFactory(
            CustomNavigatorParam(
                requireActivity(),
                childFragmentManager,
                R.id.fragmentContainer
            ) {

            })




    override fun provideDiModule(): DI.Module = doctorSearchContainerDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDoctorsearchContainerBinding = ViewDoctorsearchContainerBinding.inflate(inflater, container, false)


    override fun render(state: DoctorSearchContainerState) = Unit

    override fun provideViewModel(): DoctorSearchContainerViewModel = viewModelFactory(Unit)
    companion object {

        fun getNewInstance(): DoctorSearchContainerView {
            return DoctorSearchContainerView().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }

}
