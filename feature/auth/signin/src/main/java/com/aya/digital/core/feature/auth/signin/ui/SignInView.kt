package com.aya.digital.core.feature.auth.signin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.feature.auth.signin.databinding.ViewSigninBinding
import com.aya.digital.core.feature.auth.signin.di.signInDiModule
import com.aya.digital.core.feature.auth.signin.ui.model.SignInStateTransformer
import com.aya.digital.core.feature.auth.signin.ui.model.SignInUiModel
import com.aya.digital.core.feature.auth.signin.viewmodel.SignInState
import com.aya.digital.core.feature.auth.signin.viewmodel.SignInViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.fields.emailphone.ui.FilledButtonDelegateListeners
import com.aya.digital.core.ui.delegates.auth.signin.fields.ui.SignInFieldsDelegateListener
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class SignInView :
    DiFragment<ViewSigninBinding, SignInViewModel, SignInState, BaseSideEffect, SignInUiModel, SignInStateTransformer>() {

    private val viewModelFactory: ((Unit) -> SignInViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> SignInStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        SignInAdapter(
            SignInAdapterListeners(
                FilledButtonDelegateListeners { },
                SignInFieldsDelegateListener({},{},{})
            )
        )
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        recyclers.add(binding.recycler)
        with(binding.recycler) {
            itemAnimator = null
            setHasFixedSize(false)
            isNestedScrollingEnabled = false

            val lm = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )

            layoutManager = lm

        }
    }

    override fun provideDiModule(): DI.Module = signInDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewSigninBinding = ViewSigninBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: SignInState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): SignInViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): SignInStateTransformer =
        stateTransformerFactory(Unit)

}
