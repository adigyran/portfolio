package com.aya.digital.core.feature.profile.insurance.add.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.feature.profile.insurance.add.databinding.ViewProfileInsuranceAddBinding
import com.aya.digital.core.feature.profile.insurance.add.di.profileInsuranceAddDiModule
import com.aya.digital.core.feature.profile.insurance.add.ui.model.ProfileInsuranceAddStateTransformer
import com.aya.digital.core.feature.profile.insurance.add.ui.model.ProfileInsuranceAddUiModel
import com.aya.digital.core.feature.profile.insurance.add.viewmodel.ProfileInsuranceAddState
import com.aya.digital.core.feature.profile.insurance.add.viewmodel.ProfileInsuranceAddViewModel
import com.aya.digital.core.mvi.BaseSideEffect
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.components.fields.name.model.ui.NameFieldDelegateListeners
import com.aya.digital.core.ui.delegates.components.fields.name.model.ui.nameFieldDelegate
import com.aya.digital.core.ui.delegates.profile.emergencycontactinfo.ui.insurancePolicyPhotoDelegate
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class ProfileInsuranceAddView :
    DiFragment<ViewProfileInsuranceAddBinding, ProfileInsuranceAddViewModel, ProfileInsuranceAddState, BaseSideEffect, ProfileInsuranceAddUiModel, ProfileInsuranceAddStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileInsuranceAddViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileInsuranceAddStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate { nameFieldDelegate(NameFieldDelegateListeners(viewModel::nameFieldChanged)) }
            delegate {
                insurancePolicyPhotoDelegate(
                    viewModel::photoClicked,
                    viewModel::photoMoreClicked
                )
            }
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

    override fun provideDiModule(): DI.Module = profileInsuranceAddDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileInsuranceAddBinding =
        ViewProfileInsuranceAddBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: BaseSideEffect) = Unit

    override fun render(state: ProfileInsuranceAddState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): ProfileInsuranceAddViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileInsuranceAddStateTransformer =
        stateTransformerFactory(Unit)

}
