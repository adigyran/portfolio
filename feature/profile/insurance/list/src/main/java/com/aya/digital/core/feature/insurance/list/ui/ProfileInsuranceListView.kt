package com.aya.digital.core.feature.insurance.list.ui

import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.designsystem.R
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.insurance.list.di.profileInsuranceListDiModule
import com.aya.digital.core.feature.insurance.list.ui.model.ProfileInsuranceListStateTransformer
import com.aya.digital.core.feature.insurance.list.ui.model.ProfileInsuranceListUiModel
import com.aya.digital.core.feature.insurance.list.viewmodel.ProfileInsuranceListState
import com.aya.digital.core.feature.insurance.list.viewmodel.ProfileInsuranceListViewModel
import com.aya.digital.core.feature.profile.insurance.list.databinding.ViewProfileInsuranceListBinding
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.ext.SpannableObject
import com.aya.digital.core.ui.base.ext.colors
import com.aya.digital.core.ui.base.ext.createSpannableText
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.base.utils.LinkTouchMovementMethod
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.ui.insurancePolicyDelegate
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class ProfileInsuranceListView :
    DiFragment<ViewProfileInsuranceListBinding, ProfileInsuranceListViewModel, ProfileInsuranceListState, BaseSideEffect, ProfileInsuranceListUiModel, ProfileInsuranceListStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileInsuranceListViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileInsuranceListStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { insurancePolicyDelegate(viewModel::insuranceItemClicked) }
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

    override fun provideDiModule(): DI.Module = profileInsuranceListDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileInsuranceListBinding =
        ViewProfileInsuranceListBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: ProfileInsuranceListState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): ProfileInsuranceListViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileInsuranceListStateTransformer =
        stateTransformerFactory(Unit)

}
