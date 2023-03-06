package com.aya.digital.core.feature.profile.security.changeemailphone.ui

import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.profile.security.changeemailphone.databinding.ViewProfileSecurityChangeEmailphoneBinding
import com.aya.digital.core.feature.profile.security.changeemailphone.di.ProfileSecurityChangeEmailPhoneDiModule
import com.aya.digital.core.feature.profile.security.changeemailphone.ui.model.ProfileSecurityChangeEmailPhoneStateTransformer
import com.aya.digital.core.feature.profile.security.changeemailphone.ui.model.ProfileSecurityChangeEmailPhoneUiModel
import com.aya.digital.core.feature.profile.security.changeemailphone.viewmodel.ProfileSecurityChangeEmailPhoneState
import com.aya.digital.core.feature.profile.security.changeemailphone.viewmodel.ProfileSecurityChangeEmailPhoneViewModel
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

class ProfileSecurityChangeEmailPhoneView :
    DiFragment<ViewProfileSecurityChangeEmailphoneBinding, ProfileSecurityChangeEmailPhoneViewModel, ProfileSecurityChangeEmailPhoneState, BaseSideEffect, ProfileSecurityChangeEmailPhoneUiModel, ProfileSecurityChangeEmailPhoneStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileSecurityChangeEmailPhoneViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileSecurityChangeEmailPhoneStateTransformer) by kodein.on(
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

    override fun provideDiModule(): DI.Module = ProfileSecurityChangeEmailPhoneDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileSecurityChangeEmailphoneBinding = ViewProfileSecurityChangeEmailphoneBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: ProfileSecurityChangeEmailPhoneState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): ProfileSecurityChangeEmailPhoneViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileSecurityChangeEmailPhoneStateTransformer =
        stateTransformerFactory(Unit)

}
