package com.aya.digital.core.feature.profile.security.changepassword.ui

import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.profile.security.changepassword.databinding.ViewProfileChangePasswordBinding
import com.aya.digital.core.feature.profile.security.changepassword.di.profileSecurityChangePasswordDiModule
import com.aya.digital.core.feature.profile.security.changepassword.ui.model.ProfileSecurityChangePasswordStateTransformer
import com.aya.digital.core.feature.profile.security.changepassword.ui.model.ProfileSecurityChangePasswordUiModel
import com.aya.digital.core.feature.profile.security.changepassword.viewmodel.ProfileSecurityChangePasswordState
import com.aya.digital.core.feature.profile.security.changepassword.viewmodel.ProfileSecurityChangePasswordViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.ext.SpannableObject
import com.aya.digital.core.ui.base.ext.colors
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class ProfileSecurityChangePasswordView :
    DiFragment<ViewProfileChangePasswordBinding, ProfileSecurityChangePasswordViewModel, ProfileSecurityChangePasswordState, BaseSideEffect, ProfileSecurityChangePasswordUiModel, ProfileSecurityChangePasswordStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileSecurityChangePasswordViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileSecurityChangePasswordStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {


        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
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

        }
    }

    private fun prepareDescription() {
        //Don't have an account yet? Sign Up
        binding.descrLabl.movementMethod = LinkTouchMovementMethod()
        val description = "Don't have an account yet? %s".createSpannableText(
            colors[R.color.button_text_blue],
            colors[R.color.button_bg_dark_blue],
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
            listOf(SpannableObject("Sign Up", { signUp() }))
        )
        binding.descrLabl.text = description
    }

    private fun signUp() = viewModel.onSignUpClicked()

    override fun provideDiModule(): DI.Module = profileSecurityChangePasswordDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileChangePasswordBinding = ViewProfileChangePasswordBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: ProfileSecurityChangePasswordState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): ProfileSecurityChangePasswordViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileSecurityChangePasswordStateTransformer =
        stateTransformerFactory(Unit)

}
