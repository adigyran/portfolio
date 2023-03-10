package com.aya.digital.core.feature.tabviews.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aya.digital.core.feature.tabviews.home.di.homeDiModule
import com.aya.digital.core.feature.tabviews.home.ui.model.HomeStateTransformer
import com.aya.digital.core.feature.tabviews.home.ui.model.HomeUiModel
import com.aya.digital.core.feature.tabviews.home.viewmodel.HomeState
import com.aya.digital.core.feature.auth.signin.viewmodel.HomeViewModel
import com.aya.digital.core.feature.tabviews.home.databinding.ViewHomeBinding
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class HomeView :
    DiFragment<ViewHomeBinding, HomeViewModel, HomeState, BaseSideEffect, HomeUiModel, HomeStateTransformer>() {

    private val viewModelFactory: ((Unit) -> HomeViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> HomeStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {


        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)

    }


    override fun provideDiModule(): DI.Module = homeDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewHomeBinding = ViewHomeBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) {
        super.sideEffect(sideEffect)
    }

    override fun render(state: HomeState) {
        stateTransformer(state).data?.let {

        }
    }

    override fun provideViewModel(): HomeViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): HomeStateTransformer =
        stateTransformerFactory(Unit)

}
