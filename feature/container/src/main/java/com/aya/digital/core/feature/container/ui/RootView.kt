package com.aya.digital.core.feature.container.ui

import android.view.LayoutInflater
import com.aya.digital.core.baseresources.databinding.ViewFragmentContainerBinding
import com.aya.digital.core.feature.container.viewmodel.RootContainerState
import com.aya.digital.core.feature.container.viewmodel.RootContainerViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.uibase.baseviews.DiActivity
import com.github.terrakok.cicerone.Navigator
import org.kodein.di.DI

class RootView : DiActivity<ViewFragmentContainerBinding,RootContainerViewModel,RootContainerState,BaseSideEffect>() {

    override val viewModel: RootContainerViewModel
        get() = TODO("Not yet implemented")

    override fun provideNavigator(): Navigator {
        TODO("Not yet implemented")
    }

    override fun provideCoordinator(): Coordinator {
        TODO("Not yet implemented")
    }

    override fun provideDiModule(): DI.Module {
        TODO("Not yet implemented")
    }

    override fun provideViewBinding(inflater: LayoutInflater): ViewFragmentContainerBinding  =
        ViewFragmentContainerBinding.inflate(inflater)

    override fun sideEffect(sideEffect: BaseSideEffect) {
        TODO("Not yet implemented")
    }

    override fun render(state: RootContainerState) {
        TODO("Not yet implemented")
    }
}