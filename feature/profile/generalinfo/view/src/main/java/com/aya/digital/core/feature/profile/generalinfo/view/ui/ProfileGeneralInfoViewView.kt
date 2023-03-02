package com.aya.digital.core.feature.profile.generalinfo.view.ui

import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.profile.generalinfo.edit.databinding.ViewProfileGeneralinfoViewBinding
import com.aya.digital.core.feature.profile.generalinfo.view.di.profileGeneralInfoViewDiModule
import com.aya.digital.core.feature.profile.generalinfo.view.ui.model.ProfileGeneralInfoViewStateTransformer
import com.aya.digital.core.feature.profile.generalinfo.view.ui.model.ProfileGeneralInfoViewUiModel
import com.aya.digital.core.feature.profile.generalinfo.view.viewmodel.ProfileGeneralInfoViewState
import com.aya.digital.core.feature.profile.generalinfo.view.viewmodel.ProfileGeneralInfoViewViewModel
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

class ProfileGeneralInfoViewView :
    DiFragment<ViewProfileGeneralinfoViewBinding, ProfileGeneralInfoViewViewModel, ProfileGeneralInfoViewState, BaseSideEffect, ProfileGeneralInfoViewUiModel, ProfileGeneralInfoViewStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileGeneralInfoViewViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileGeneralInfoViewStateTransformer) by kodein.on(
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

    override fun provideDiModule(): DI.Module = profileGeneralInfoViewDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileGeneralinfoViewBinding = ViewProfileGeneralinfoViewBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: ProfileGeneralInfoViewState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): ProfileGeneralInfoViewViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileGeneralInfoViewStateTransformer =
        stateTransformerFactory(Unit)

}
