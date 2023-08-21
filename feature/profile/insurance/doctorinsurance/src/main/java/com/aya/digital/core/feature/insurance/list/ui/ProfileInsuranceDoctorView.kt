package com.aya.digital.core.feature.insurance.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.insurance.list.di.profileInsuranceDoctorDiModule
import com.aya.digital.core.feature.insurance.list.ui.model.ProfileInsuranceDoctorStateTransformer
import com.aya.digital.core.feature.insurance.list.ui.model.ProfileInsuranceDoctorUiModel
import com.aya.digital.core.feature.insurance.list.viewmodel.ProfileInsuranceDoctorSideEffects
import com.aya.digital.core.feature.insurance.list.viewmodel.ProfileInsuranceDoctorState
import com.aya.digital.core.feature.insurance.list.viewmodel.ProfileInsuranceDoctorViewModel
import com.aya.digital.core.feature.profile.insurance.doctorinsurance.databinding.ViewProfileInsuranceDoctorBinding
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.profile.insurance.ui.InsurancePoliciesDelegate
import com.aya.digital.core.ui.delegates.profile.insurance.ui.InsurancePolicyDelegate
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class ProfileInsuranceDoctorView :
    DiFragment<ViewProfileInsuranceDoctorBinding, ProfileInsuranceDoctorViewModel, ProfileInsuranceDoctorState, ProfileInsuranceDoctorSideEffects, ProfileInsuranceDoctorUiModel, ProfileInsuranceDoctorStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfileInsuranceDoctorViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfileInsuranceDoctorStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate {
                InsurancePoliciesDelegate(
                    viewModel::insuranceItemClicked,
                )
            }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        recyclers.add(binding.recycler)
        binding.toolbar.backButton bindClick {viewModel.onBack()}
        binding.toolbar.title.text = "Insurance"
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
            addItemDecoration(ProfileInsuranceDoctorDecoration())
        }
    }

    override fun provideDiModule(): DI.Module = profileInsuranceDoctorDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfileInsuranceDoctorBinding =
        ViewProfileInsuranceDoctorBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: ProfileInsuranceDoctorSideEffects) =
        when(sideEffect)
        {
            is ProfileInsuranceDoctorSideEffects.Error -> processErrorSideEffect(sideEffect.error)
            is ProfileInsuranceDoctorSideEffects.ShowInsuranceActionsDialog -> showInsuranceActionsDialog(sideEffect.insuranceId)
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

    override fun render(state: ProfileInsuranceDoctorState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): ProfileInsuranceDoctorViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfileInsuranceDoctorStateTransformer =
        stateTransformerFactory(Unit)

}
