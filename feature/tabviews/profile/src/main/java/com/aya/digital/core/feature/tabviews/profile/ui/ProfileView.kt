package com.aya.digital.core.feature.tabviews.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup

import com.aya.digital.core.feature.tabviews.profile.ui.model.ProfileStateTransformer
import com.aya.digital.core.feature.tabviews.profile.ui.model.ProfileUiModel
import com.aya.digital.core.feature.tabviews.profile.viewmodel.ProfileState
import com.aya.digital.core.feature.tabviews.profile.viewmodel.ProfileViewModel
import com.aya.digital.core.feature.tabviews.profile.databinding.ViewProfileBinding
import com.aya.digital.core.feature.tabviews.profile.di.profileDiModule
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class ProfileView :
    DiFragment<ViewProfileBinding, ProfileViewModel, ProfileState, BaseSideEffect, ProfileUiModel, ProfileStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {


        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
    }

    override fun provideDiModule(): DI.Module = profileDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileBinding = ViewProfileBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: ProfileState) {
        stateTransformer(state).data?.let {

        }
    }

    override fun provideViewModel(): ProfileViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileStateTransformer =
        stateTransformerFactory(Unit)

}
