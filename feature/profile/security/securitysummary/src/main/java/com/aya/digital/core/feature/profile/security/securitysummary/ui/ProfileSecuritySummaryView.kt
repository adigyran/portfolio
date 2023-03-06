package com.aya.digital.core.feature.profile.security.securitysummary.ui

import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.profile.security.securitysummary.databinding.ViewProfileSecuritySummaryBinding
import com.aya.digital.core.feature.profile.security.securitysummary.di.profileSecuritySummaryDiModule
import com.aya.digital.core.feature.profile.security.securitysummary.ui.model.ProfileSecuritySummaryStateTransformer
import com.aya.digital.core.feature.profile.security.securitysummary.ui.model.ProfileSecuritySummaryUiModel
import com.aya.digital.core.feature.profile.security.securitysummary.viewmodel.ProfileSecuritySummaryState
import com.aya.digital.core.feature.profile.security.securitysummary.viewmodel.ProfileSecuritySummaryViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.ext.SpannableObject
import com.aya.digital.core.ui.base.ext.colors
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.ui.securitySummaryDelegate
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class ProfileSecuritySummaryView :
    DiFragment<ViewProfileSecuritySummaryBinding, ProfileSecuritySummaryViewModel, ProfileSecuritySummaryState, BaseSideEffect, ProfileSecuritySummaryUiModel, ProfileSecuritySummaryStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileSecuritySummaryViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileSecuritySummaryStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { securitySummaryDelegate(viewModel::itemClicked) }
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

    override fun provideDiModule(): DI.Module = profileSecuritySummaryDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileSecuritySummaryBinding = ViewProfileSecuritySummaryBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: ProfileSecuritySummaryState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): ProfileSecuritySummaryViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileSecuritySummaryStateTransformer =
        stateTransformerFactory(Unit)

}
