package com.aya.digital.core.feature.profile.clinicinfo.ui

import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.profile.clinicinfo.di.profileClinicInfoDiModule
import com.aya.digital.core.feature.profile.clinicinfo.ui.model.ProfileClinicInfoStateTransformer
import com.aya.digital.core.feature.profile.clinicinfo.ui.model.ProfileClinicInfoUiModel
import com.aya.digital.core.feature.profile.clinicinfo.viewmodel.ProfileClinicInfoState
import com.aya.digital.core.feature.profile.clinicinfo.viewmodel.ProfileClinicInfoViewModel
import com.aya.digital.core.feature.profile.security.clinicinfo.databinding.ViewProfileClinicInfoBinding
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.ext.SpannableObject
import com.aya.digital.core.ext.colors
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class ProfileClinicInfoView :
    DiFragment<ViewProfileClinicInfoBinding, ProfileClinicInfoViewModel, ProfileClinicInfoState, BaseSideEffect, ProfileClinicInfoUiModel, ProfileClinicInfoStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileClinicInfoViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileClinicInfoStateTransformer) by kodein.on(
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
            binding.descrLabl.context,
            R.style.TextAppearance_App_Body_DescriptionMiniText,
            R.style.TextAppearance_App_ButtonText_Default,
            listOf(SpannableObject("Sign Up", { signUp() }))
        )
        binding.descrLabl.text = description
    }

    private fun signUp() = viewModel.onSignUpClicked()

    override fun provideDiModule(): DI.Module = profileClinicInfoDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileClinicInfoBinding =
        ViewProfileClinicInfoBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: ProfileClinicInfoState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): ProfileClinicInfoViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileClinicInfoStateTransformer =
        stateTransformerFactory(Unit)

}
