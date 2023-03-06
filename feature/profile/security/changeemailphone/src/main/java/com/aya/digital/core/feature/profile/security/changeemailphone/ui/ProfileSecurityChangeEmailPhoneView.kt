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
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.EmailPhoneDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.emailphone.ui.emailPhoneFieldDelegate
import com.aya.digital.core.ui.delegates.components.labels.headline.ui.headlineTwoLineLabelDelegate
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
            delegate { headlineTwoLineLabelDelegate() }
            delegate { emailPhoneFieldDelegate(EmailPhoneDelegateListeners(inputFieldChangeListener = viewModel::emailChanged)) }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        recyclers.add(binding.recycler)
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
