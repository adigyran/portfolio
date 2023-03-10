package com.aya.digital.feature.auth.chooser.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.auth.chooser.buttons.ui.ButtonsDelegateListeners
import com.aya.digital.core.ui.delegates.auth.chooser.buttons.ui.chooserButtonsDelegate
import com.aya.digital.core.ui.delegates.auth.chooser.description.ui.DescriptionDelegateListeners
import com.aya.digital.core.ui.delegates.auth.chooser.description.ui.chooserDescriptionDelegate
import com.aya.digital.feature.auth.chooser.databinding.ViewAuthChooserBinding
import com.aya.digital.feature.auth.chooser.di.authChooserDiModule
import com.aya.digital.feature.auth.chooser.ui.model.AuthChooserStateTransformer
import com.aya.digital.feature.auth.chooser.ui.model.AuthChooserUiModel
import com.aya.digital.feature.auth.chooser.viewmodel.AuthChooserState
import com.aya.digital.feature.auth.chooser.viewmodel.AuthChooserViewModel
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

internal class AuthChooserView :
    DiFragment<ViewAuthChooserBinding, AuthChooserViewModel, AuthChooserState, BaseSideEffect, AuthChooserUiModel, AuthChooserStateTransformer>() {

    private val viewModelFactory: ((Unit) -> AuthChooserViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> AuthChooserStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { chooserDescriptionDelegate(DescriptionDelegateListeners({},{},{})) }
            delegate { chooserButtonsDelegate(ButtonsDelegateListeners(viewModel::onSignInClicked,viewModel::onSignUpClicked)) }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        recyclers.add(binding.recycler)
        with(binding.recycler) {
            itemAnimator = null
            setHasFixedSize(true)
            isNestedScrollingEnabled = false

            val lm = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                true
            )
            layoutManager = lm

        }
    }

    override fun provideDiModule(): DI.Module = authChooserDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewAuthChooserBinding = ViewAuthChooserBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: AuthChooserState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): AuthChooserViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): AuthChooserStateTransformer =
        stateTransformerFactory(Unit)

}
