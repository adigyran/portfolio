package com.aya.digital.core.feature.insurance.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.insurance.list.di.profileInsuranceListDiModule
import com.aya.digital.core.feature.insurance.list.ui.model.ProfileInsuranceListStateTransformer
import com.aya.digital.core.feature.insurance.list.ui.model.ProfileInsuranceListUiModel
import com.aya.digital.core.feature.insurance.list.viewmodel.ProfileInsuranceListSideEffects
import com.aya.digital.core.feature.insurance.list.viewmodel.ProfileInsuranceListState
import com.aya.digital.core.feature.insurance.list.viewmodel.ProfileInsuranceListViewModel
import com.aya.digital.core.feature.profile.insurance.list.databinding.ViewProfileInsuranceListBinding
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.profile.insurance.ui.InsurancePolicyDelegate
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class ProfileInsuranceListView :
    DiFragment<ViewProfileInsuranceListBinding, ProfileInsuranceListViewModel, ProfileInsuranceListState, ProfileInsuranceListSideEffects, ProfileInsuranceListUiModel, ProfileInsuranceListStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileInsuranceListViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileInsuranceListStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate {
                InsurancePolicyDelegate(
                    viewModel::insuranceItemClicked,
                    viewModel::insuranceItemMoreClicked
                )
            }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        recyclers.add(binding.recycler)
        binding.addInsuranceBtn bindClick { viewModel.addInsuranceClicked() }
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
            addItemDecoration(ProfileInsuranceListDecoration())
        }
    }

    override fun provideDiModule(): DI.Module = profileInsuranceListDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileInsuranceListBinding =
        ViewProfileInsuranceListBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: ProfileInsuranceListSideEffects) =
        when(sideEffect)
        {
            is ProfileInsuranceListSideEffects.Error -> processErrorSideEffect(sideEffect.error)
            is ProfileInsuranceListSideEffects.ShowInsuranceActionsDialog -> showInsuranceActionsDialog(sideEffect.insuranceId)
        }

    private fun showInsuranceActionsDialog(insuranceId: Int) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete?")
            .setMessage("Are you sure you want to remove this insurance?")
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton("Delete") { dialog, which ->
                viewModel.deleteInsurance(insuranceId)
                dialog.dismiss()
            }
            .show()
    }

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
