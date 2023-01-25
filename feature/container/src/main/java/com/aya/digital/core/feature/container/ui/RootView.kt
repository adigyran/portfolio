package com.aya.digital.core.feature.container.ui

import android.os.Bundle
import android.provider.DocumentsContract.Root
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.aya.digital.core.baseresources.databinding.ViewFragmentContainerBinding
import com.aya.digital.core.feature.container.di.RootNavigatorParam
import com.aya.digital.core.feature.container.di.rootContainerDiModule
import com.aya.digital.core.feature.container.navigation.RootNavigator
import com.aya.digital.core.feature.container.viewmodel.RootContainerState
import com.aya.digital.core.feature.container.viewmodel.RootContainerViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.navigation.coordinator.Coordinator
import com.aya.digital.core.uibase.baseviews.DiActivity
import com.github.terrakok.cicerone.Navigator
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on
import timber.log.Timber

class RootView : DiActivity<ViewFragmentContainerBinding,RootContainerViewModel,RootContainerState,BaseSideEffect>() {

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
    }

    override fun sideEffect(sideEffect: BaseSideEffect) {

    }

    override fun render(state: RootContainerState) {

    }

    override fun provideDiModule() = rootContainerDiModule()

    override fun provideViewBinding(inflater: LayoutInflater): ViewFragmentContainerBinding  =
        ViewFragmentContainerBinding.inflate(inflater)



    override fun provideViewModel(): RootContainerViewModel = viewModelFactory(Unit)


    override fun provideNavigator(): Navigator = navigatorFactory(RootNavigatorParam(this, binding.fragmentContainer.id))

    override fun provideCoordinator(): Coordinator = coordinatorFactory(supportFragmentManager)

}