package com.aya.digital.core.feature.prescriptions.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aya.digital.core.ext.bindClick
import com.aya.digital.core.feature.prescriptions.list.di.profilePrescriptionsListDiModule
import com.aya.digital.core.feature.prescriptions.list.ui.model.ProfilePrescriptionsListStateTransformer
import com.aya.digital.core.feature.prescriptions.list.ui.model.ProfilePrescriptionsListUiModel
import com.aya.digital.core.feature.prescriptions.list.viewmodel.ProfilePrescriptionsListSideEffects
import com.aya.digital.core.feature.prescriptions.list.viewmodel.ProfilePrescriptionsListState
import com.aya.digital.core.feature.prescriptions.list.viewmodel.ProfilePrescriptionsListViewModel
import com.aya.digital.core.feature.profile.prescriptions.list.databinding.ViewProfilePrescriptionsListBinding
import com.aya.digital.core.ui.adapters.base.BaseDelegateAdapter
import com.aya.digital.core.ui.base.screens.DiFragment
import com.aya.digital.core.ui.delegates.profile.insurance.ui.InsurancePolicyDelegate
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.kodein.di.DI
import org.kodein.di.factory
import org.kodein.di.on

class ProfilePrescriptionsListView :
    DiFragment<ViewProfilePrescriptionsListBinding, ProfilePrescriptionsListViewModel, ProfilePrescriptionsListState, ProfilePrescriptionsListSideEffects, ProfilePrescriptionsListUiModel, ProfilePrescriptionsListStateTransformer>() {

    private val viewModelFactory: ((Unit) -> ProfilePrescriptionsListViewModel) by kodein.on(
        context = this
    ).factory()

    private val stateTransformerFactory: ((Unit) -> ProfilePrescriptionsListStateTransformer) by kodein.on(
        context = this
    ).factory()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        BaseDelegateAdapter.create {
            delegate {
                InsurancePolicyDelegate(
                    {id: Int ->
                    },
                    {id: Int ->  }
                )
            }
        }
    }

    override fun prepareCreatedUi(savedInstanceState: Bundle?) {
        super.prepareCreatedUi(savedInstanceState)
        recyclers.add(binding.recycler)
        binding.toolbar.backButton bindClick {viewModel.onBack()}
        binding.toolbar.title.text = "Insurance"
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
            addItemDecoration(ProfilePrescriptionsListDecoration())
        }
    }

    override fun provideDiModule(): DI.Module = profilePrescriptionsListDiModule(tryTyGetParentRouter())

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewProfilePrescriptionsListBinding =
        ViewProfilePrescriptionsListBinding.inflate(inflater, container, false)

    override fun sideEffect(sideEffect: ProfilePrescriptionsListSideEffects) =
        when(sideEffect)
        {
            is ProfilePrescriptionsListSideEffects.Error -> processErrorSideEffect(sideEffect.error)
        }



    override fun render(state: ProfilePrescriptionsListState) {
        stateTransformer(state).data?.let {
            adapter.items = it
            if (binding.recycler.adapter == null) {
                binding.recycler.swapAdapter(adapter, true)
            }
        }
    }

    override fun provideViewModel(): ProfilePrescriptionsListViewModel = viewModelFactory(Unit)
    override fun provideStateTransformer(): ProfilePrescriptionsListStateTransformer =
        stateTransformerFactory(Unit)

}
