package com.aya.digital.core.feature.profile.generalinfo.edit.ui

import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.profile.generalinfo.edit.databinding.ViewProfileGeneralinfoEditBinding
import com.aya.digital.core.feature.profile.generalinfo.edit.di.profileGeneralInfoEditDiModule
import com.aya.digital.core.feature.profile.generalinfo.edit.ui.model.ProfileGeneralInfoEditStateTransformer
import com.aya.digital.core.feature.profile.generalinfo.edit.ui.model.ProfileGeneralInfoEditUiModel
import com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel.ProfileGeneralInfoEditState
import com.aya.digital.core.feature.profile.generalinfo.edit.viewmodel.ProfileGeneralInfoEditViewModel
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

class ProfileGeneralInfoEditView :
    DiFragment<ViewProfileGeneralinfoEditBinding, ProfileGeneralInfoEditViewModel, ProfileGeneralInfoEditState, BaseSideEffect, ProfileGeneralInfoEditUiModel, ProfileGeneralInfoEditStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileGeneralInfoEditViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileGeneralInfoEditStateTransformer) by kodein.on(
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

    override fun provideDiModule(): DI.Module = profileGeneralInfoEditDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileGeneralinfoEditBinding = ViewProfileGeneralinfoEditBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: ProfileGeneralInfoEditState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): ProfileGeneralInfoEditViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileGeneralInfoEditStateTransformer =
        stateTransformerFactory(Unit)

}
