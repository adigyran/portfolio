package com.aya.digital.feature.auth.container.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.baseresources.databinding.ViewFragmentContainerBinding
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.navigation.CustomNavigator
import com.aya.digital.core.navigation.di.CustomNavigatorParam
import com.aya.digital.core.ui.base.screens.DiBottomSheetDialogFragment
import com.aya.digital.core.ui.base.screens.DiContainerFragment
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.feature.auth.container.R
import com.aya.digital.feature.auth.container.di.authContainerDiModule
import com.aya.digital.feature.auth.container.navigation.AuthContainerNavigationEvents
import com.aya.digital.feature.auth.container.viewmodel.AuthContainerState
import com.aya.digital.feature.auth.container.viewmodel.AuthContainerViewModel
import com.github.terrakok.cicerone.Navigator
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class AuthContainerView :
    DiContainerFragment<ViewFragmentContainerBinding, AuthContainerViewModel, AuthContainerState, BaseSideEffect>() {


    private val navigatorFactory: ((param: CustomNavigatorParam) -> CustomNavigator) by kodein.on(
        context = this
    ).factory()
    private val viewModelFactory: ((Unit) -> AuthContainerViewModel) by kodein.on(
        context = this
    ).factory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
        openDefaultScreen()

    }

    private fun openDefaultScreen() {
        coordinatorHolder.sendEvent(AuthContainerNavigationEvents.OpenDefaultScreen)
    }


    override fun provideNavigator(): Navigator =
        navigatorFactory(
            CustomNavigatorParam(
                requireActivity(),
                childFragmentManager,
                com.aya.digital.core.baseresources.R.id.fragmentContainer
            ) {

            })

    override fun provideDiModule() = authContainerDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewFragmentContainerBinding =
        ViewFragmentContainerBinding.inflate(inflater, container, false)

    override fun provideViewModel() = viewModelFactory(Unit)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: AuthContainerState) = Unit

    companion object {

        fun getNewInstance(): AuthContainerView {
            return AuthContainerView().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }
}

