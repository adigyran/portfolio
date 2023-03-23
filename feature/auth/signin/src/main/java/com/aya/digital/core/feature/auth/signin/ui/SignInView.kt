package com.aya.digital.core.feature.auth.signin.ui

import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.auth.signin.databinding.ViewSigninBinding
import com.aya.digital.core.feature.auth.signin.di.signInDiModule
import com.aya.digital.core.feature.auth.signin.ui.model.SignInStateTransformer
import com.aya.digital.core.feature.auth.signin.ui.model.SignInUiModel
import com.aya.digital.core.feature.auth.signin.viewmodel.SignInState
import com.aya.digital.core.feature.auth.signin.viewmodel.SignInViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.ext.SpannableObject
import com.aya.digital.core.ext.colors
import com.aya.digital.core.feature.auth.signin.viewmodel.SignInSideEffects
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.EmailPhoneDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.EmailPhoneFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.password.ui.PasswordFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.password.ui.PasswordFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.*
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class SignInView :
    DiFragment<ViewSigninBinding, SignInViewModel, SignInState, SignInSideEffects, SignInUiModel, SignInStateTransformer>() {

    private val viewModelFactory: ((Unit) -> SignInViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> SignInStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { HeadlineLabelDelegate(HeadlineLabelDelegateListeners()) }
            delegate { SpannableHelperLabelDelegate(SpannableHelperLabelDelegateListeners{viewModel.restorePassword()}) }
            delegate { EmailPhoneFieldDelegate(EmailPhoneDelegateListeners(viewModel::emailChanged)) }
            delegate { PasswordFieldDelegate(PasswordFieldDelegateListeners(viewModel::passwordChanged)) }

        }
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        if(savedInstanceState==null) {
            prepareDescription()
            recyclers.add(binding.recycler)
            binding.signInBtn bindClick { viewModel.onSignInClicked() }
            with(binding.recycler) {
                itemAnimator = null
                setHasFixedSize(true)
                setItemViewCacheSize(30)
                isNestedScrollingEnabled = false

                val lm = LinearLayoutManager(
                    context,
                    RecyclerView.VERTICAL,
                    false
                )

                layoutManager = lm
                addItemDecoration(SignInDecoration())
            }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)

    }

    private fun prepareDescription() {
        //Don't have an account yet? Sign Up
        binding.descrLabl.movementMethod = LinkTouchMovementMethod()
        val description = "Don't have an account yet? %s".createSpannableText(
            colors[R.color.button_text_blue],
            colors[R.color.button_bg_dark_blue],
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
            binding.descrLabl.context,
            R.style.TextAppearance_App_Body_DescriptionMiniText,
            R.style.TextAppearance_App_ButtonText_Default,
            listOf(SpannableObject("Sign Up", { signUp() }))
        )
        binding.descrLabl.text = description
    }

    private fun signUp() = viewModel.onSignUpClicked()

    override fun provideDiModule(): DI.Module = signInDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewSigninBinding = ViewSigninBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: SignInSideEffects)= when(sideEffect)
    {
        is SignInSideEffects.Error -> {
            processErrorSideEffect(sideEffect.error)
        }
    }

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
