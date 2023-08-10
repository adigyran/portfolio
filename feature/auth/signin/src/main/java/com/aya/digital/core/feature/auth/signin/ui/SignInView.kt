package com.aya.digital.core.feature.auth.signin.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
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
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.ext.SpannableObject
import com.aya.digital.core.ext.colors
import com.aya.digital.core.feature.auth.signin.viewmodel.SignInSideEffects
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.EmailDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.EmailFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.PhoneFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.password.ui.PasswordFieldDelegate
import com.aya.digital.core.ui.delegates.components.fields.password.ui.PasswordFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.*
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
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
            delegate { EmailFieldDelegate(EmailDelegateListeners(viewModel::emailChanged)) }
            delegate { PasswordFieldDelegate(PasswordFieldDelegateListeners(viewModel::passwordChanged)) }

        }
    }

    private val authResponse = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val dataIntent = it.data
        if (dataIntent != null) {
            handleAuthResponseIntent(dataIntent)
        }
        if (it.resultCode == Activity.RESULT_CANCELED) {
            viewModel.loginCanceled()
        }
    }

    override fun prepareUi(savedInstanceState: Bundle?) {
        super.prepareUi(savedInstanceState)
        if(savedInstanceState==null) {
            prepareDescription()
            recyclers.add(binding.recycler)
            binding.signInBtn bindClick { viewModel.onSignInClicked() }
            binding.toolbarLogo.backButton bindClick {viewModel.onBack()}
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
        binding.signInOauthBtn bindClick {viewModel.onSignInOathClicked()}
    }

    private fun signUp() = viewModel.onSignUpClicked()

    private fun handleAuthResponseIntent(intent: Intent) {
        val exception = AuthorizationException.fromIntent(intent)
        val tokenExchangeRequest = AuthorizationResponse.fromIntent(intent)
            ?.createTokenExchangeRequest()
        when {
            exception != null -> viewModel.onAuthCodeFailed(exception)
            tokenExchangeRequest != null -> viewModel.onAuthCodeReceived(tokenExchangeRequest)
        }
    }
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
        is SignInSideEffects.OpenOAuthPage -> {
            authResponse.launch(sideEffect.intent)
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
