package com.aya.digital.feature.auth.signup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.fields.emailphone.ui.FilledButtonDelegateListeners
import com.aya.digital.core.ui.delegates.fields.emailphone.ui.SignInFieldsDelegateListener
import com.aya.digital.feature.auth.signup.databinding.ViewSignupBinding
import com.aya.digital.feature.auth.signup.di.signUpDiModule
import com.aya.digital.feature.auth.signup.ui.model.SignUpStateTransformer
import com.aya.digital.feature.auth.signup.ui.model.SignUpUiModel
import com.aya.digital.feature.auth.signup.viewmodel.SignUpState
import com.aya.digital.feature.auth.signup.viewmodel.SignUpViewModel
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class SignUpView :
    DiFragment<ViewSignupBinding, SignUpViewModel, SignUpState, BaseSideEffect, SignUpUiModel, SignUpStateTransformer>() {

    private val viewModelFactory: ((Unit) -> SignUpViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> SignUpStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        SignUpAdapter(
            SignUpAdapterListeners(
                signUpFields = SignInFieldsDelegateListener({}, {}, {}, {}, {}),
                buttonListeners = FilledButtonDelegateListeners { })
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

    override fun provideDiModule(): DI.Module = signUpDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewSignupBinding = ViewSignupBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: SignUpState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): SignUpViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): SignUpStateTransformer =
        stateTransformerFactory(Unit)

}
