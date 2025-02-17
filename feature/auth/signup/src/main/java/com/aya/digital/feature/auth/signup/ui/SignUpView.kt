package com.aya.digital.feature.auth.signup.ui

import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.navigation.coordinator.CoordinatorRouter
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.ext.SpannableObject
import com.aya.digital.core.ext.colors
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.EmailDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.EmailFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.PhoneFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.name.ui.NameFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.name.ui.NameFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.password.ui.PasswordFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.password.ui.PasswordFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.SelectionFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.selection.ui.SelectionFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.HeadlineLabelDelegate
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.HeadlineLabelDelegateListeners
import com.aya.digital.feature.auth.signup.databinding.ViewSignupBinding
import com.aya.digital.feature.auth.signup.di.signUpDiModule
import com.aya.digital.feature.auth.signup.ui.model.SignUpStateTransformer
import com.aya.digital.feature.auth.signup.ui.model.SignUpUiModel
import com.aya.digital.feature.auth.signup.viewmodel.SignUpSideEffects
import com.aya.digital.feature.auth.signup.viewmodel.SignUpState
import com.aya.digital.feature.auth.signup.viewmodel.SignUpViewModel
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.instance
import org.kodein.di.on

class SignUpView :
    DiFragment<ViewSignupBinding, SignUpViewModel, SignUpState, SignUpSideEffects, SignUpUiModel, SignUpStateTransformer>() {

    private val viewModelFactory: ((Unit) -> SignUpViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> SignUpStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val coordinatorHolder: CoordinatorRouter by kodein.on(context = this)
        .instance(tag = "parent_coordinator_auth_container")


    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { HeadlineLabelDelegate(HeadlineLabelDelegateListeners()) }
            delegate { EmailFieldDelegate(EmailDelegateListeners(viewModel::emailChanged)) }
            delegate { PasswordFieldDelegate(PasswordFieldDelegateListeners(viewModel::passwordChanged)) }
            delegate { NameFieldDelegate(NameFieldDelegateListeners(viewModel::nameChanged)) }
            delegate { SelectionFieldDelegate(SelectionFieldDelegateListeners(viewModel::insuranceCompanyFieldSelected)) }

        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        prepareDescription()
        recyclers.add(binding.recycler)
        binding.signUpBtn bindClick { viewModel.onSignUpClicked() }
        binding.toolbarLogo.backButton bindClick {viewModel.onBack()}
        with(binding.recycler) {
            itemAnimator = null
            setHasFixedSize(true)
            setItemViewCacheSize(10)
            isNestedScrollingEnabled = false
            val lm = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )

            layoutManager = lm
            addItemDecoration(SignUpDecoration())
        }
    }
    private fun prepareDescription() {
        binding.descrLabl.movementMethod = LinkTouchMovementMethod()
        //Already have an account? Sign In
        val description = "Already have an account? %s".createSpannableText(
            colors[R.color.button_text_blue],
            colors[R.color.button_bg_dark_blue],
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
            binding.descrLabl.context,
            R.style.TextAppearance_App_Body_DescriptionMiniText,
            R.style.TextAppearance_App_ButtonText_Default,
            listOf(SpannableObject("Sign In", { signIn() }))
        )
        binding.descrLabl.text = description

    }

    private fun signIn() = viewModel.onSignInClicked()

    override fun provideDiModule(): DI.Module = signUpDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewSignupBinding = ViewSignupBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: SignUpSideEffects) {
        when (sideEffect) {
            is SignUpSideEffects.Error -> processErrorSideEffect(sideEffect.error)
            SignUpSideEffects.AttemptToRegisterVerifiedProfile -> showErrorMsg("This email already registered")
        }
    }

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
